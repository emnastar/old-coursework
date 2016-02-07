package airline.flight;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import util.Constants;

import static util.Assertions.*;

/**
 * Contains all the nodes connected in a graph, which can be traversed,
 * searched, and other tasks related to the flight graph manipulation. This is
 * not a thread safe object, and should not have multithreading procedures
 * performed on it.
 * 
 * @author Chris, David
 */
public class FlightGraph {

	/**
	 * A map of names to nodes for this graph.
	 */
	private HashMap<String, FlightNode> nameToNode;

	/**
	 * Creates an empty graph.
	 */
	public FlightGraph() {
		nameToNode = new HashMap<>();
	}

	/**
	 * Adds flight data to the graph.
	 * 
	 * @param flightInfo
	 *            The flight information to add.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public void addFlight(FlightInformation flightInfo) {
		checkNotNull(flightInfo);

		// If the flight origin/destination do not exist, create empty nodes
		// for them so we can add connections later.
		String origin = flightInfo.getOrigin();
		if (!nameToNode.containsKey(origin)) {
			nameToNode.put(origin, new FlightNode(origin));
		}

		String destination = flightInfo.getDestination();
		if (!nameToNode.containsKey(destination)) {
			nameToNode.put(destination, new FlightNode(destination));
		}

		// Add the connection to the destination.
		FlightNode originNode = nameToNode.get(flightInfo.getOrigin());
		originNode.addConnection(flightInfo);
	}

	/**
	 * Searches for itineraries based on the data provided and gets all the
	 * valid ones.
	 * 
	 * @param departureDate
	 *            The desired departure day.
	 * 
	 * @param origin
	 *            The origin location.
	 * 
	 * @param destination
	 *            The desired destination.
	 * 
	 * @return A list of the found itinerary (this can be empty).
	 * 
	 * @throws NullPointerException
	 *             If any arguments are null.
	 * 
	 * @throws IllegalArgumentException
	 *             If the departure date is not in YYYY-MM-DD format.
	 */
	public List<Itinerary> searchForItineraries(String departureDate,
			String origin, String destination) {
		checkNotNull(departureDate);
		checkNotNull(origin);
		checkNotNull(destination);
		checkArgument(departureDate.matches("\\d{4}-\\d{2}-\\d{2}"));

		LocalDate startDate = Constants.parseDate(departureDate);
		List<List<FlightInformation>> flights = searchItinerariesBy(origin,
				destination, startDate);
		return Itinerary.convertListToItineraryList(flights);
	}

	/**
	 * Searches the graph of flights for a list of itineraries to the desired
	 * destination.
	 * 
	 * @param origin
	 *            The departing location.
	 * 
	 * @param destination
	 *            The desired destination.
	 * 
	 * @param departureDate
	 *            The desired departure day.
	 * 
	 * @return A list of itineraries (a which is a list of flight info).
	 * 
	 * @throws NullPointerException
	 *             If any of the arguments are null.
	 * 
	 * @throws IllegalArgumentException
	 *             If the origin or destination are the same.
	 */
	private List<List<FlightInformation>> searchItinerariesBy(String origin,
			String destination, LocalDate departureDate) {
		checkNotNull(origin);
		checkNotNull(destination);
		checkNotNull(departureDate);
		checkArgument(!origin.equals(destination));

		List<List<FlightInformation>> listOfFlights = new LinkedList<>();

		// Check that the origin and destination actually exist, and if so,
		// traverse graph and populate list. Otherwise return an empty list.
		boolean hasOrigin = nameToNode.containsKey(origin);
		boolean hasDestination = nameToNode.containsKey(destination);
		if (hasOrigin && hasDestination) {
			populateListOfFlights(origin, destination, departureDate,
					listOfFlights);
		}

		return listOfFlights;
	}

	/**
	 * Takes the required information and populates the list of flights so all
	 * the valid itinerary from the origin to destination are avaiable. This
	 * searches the entire graph based on the travel times and available data.
	 * 
	 * @param origin
	 *            The starting city name.
	 * 
	 * @param destination
	 *            The final city destination name.
	 * 
	 * @param departureDate
	 *            The date the client wants to depart.
	 * 
	 * @param listOfFlights
	 *            A mutable list of list of flight informations, or better put:
	 *            A list of 'flight itineraries' that will be added to from this
	 *            method.
	 */
	private void populateListOfFlights(String origin, String destination,
			LocalDate departureDate,
			List<List<FlightInformation>> listOfFlights) {
		// This will be our list of rotating itinerary that is considered 'not
		// solved' for a final path yet. We will continue adding and removing
		// elements of this list until it is empty (which means we exhausted
		// all possible paths). Any paths that resolve from the origin to
		// destination from this list will be added to the listOfFlights since
		// they are completely solved paths we can return.
		LinkedList<ItineraryBuilder> listUnsolvedPaths = new LinkedList<>();

		// Find all flights from the search time within the constant time and
		// populate a list of 1-flight itinerary lists. We need to do this
		// outside of the loop since the list requires a starting element and
		// we don't have that yet.
		assert nameToNode.containsKey(origin);
		getValidItineraryListFromNodeAt(nameToNode.get(origin), departureDate,
				listUnsolvedPaths);

		// Since the above may have possibly yielded a valid path immediately,
		// we have to check for any valid ones and add them to the completed
		// list (meaning a single flight is a valid itinerary).
		migrateCompleteItineraries(listUnsolvedPaths, listOfFlights,
				destination);

		// While the list contains elements, keep searching and putting valid
		ItineraryBuilder unsolvedItinerary;
		LinkedList<FlightInformation> foundValidFlights;
		while (listUnsolvedPaths.size() > 0) {
			// Pop the last element of the list and we'll operate on that.
			unsolvedItinerary = listUnsolvedPaths.pop();

			// Search for all viable paths from the last destination above.
			foundValidFlights = findFlightPermutations(unsolvedItinerary);

			// If there was one or more results from searching above, then
			// create new lists for each permutation, append, and add back to
			// the list. If there's no new permutations, do nothing (which
			// causes the element to be GC'd since it's a dead path).
			while (foundValidFlights.size() > 0) {
				FlightInformation validFlight = foundValidFlights.pop();

				// Append to the popped list by generating new permutations.
				ItineraryBuilder newItList = new ItineraryBuilder(
						unsolvedItinerary);
				newItList.add(validFlight);

				// If the last location is the desired final place, then it's
				// done and it can be added into the final list.
				if (newItList.getLastDestination().equals(destination)) {
					listOfFlights.add(newItList.getList());
				} else {
					// Else if not, then put it back in the list.
					listUnsolvedPaths.add(newItList);
				}
			}
		}
	}

	/**
	 * Looks for flight information data from the origin node within a constant
	 * time from the provided start search time, and populates the list of
	 * unsolved paths with the found flight paths.
	 * 
	 * @param originNode
	 *            The starting node.
	 * 
	 * @param departureDate
	 *            The starting date to search.
	 * 
	 * @param listUnsolvedPaths
	 *            The list to mutate by adding in the new paths.
	 */
	private void getValidItineraryListFromNodeAt(FlightNode originNode,
			LocalDate departureDate,
			LinkedList<ItineraryBuilder> listUnsolvedPaths) {
		assert originNode != null;
		assert departureDate != null;
		assert listUnsolvedPaths != null;
		assert listUnsolvedPaths.size() == 0;

		// For each connected node...
		for (List<FlightInformation> listInfo : originNode.getFlightData()
				.values()) {
			// For every flight from that node...
			for (FlightInformation fi : listInfo) {
				// If the node is in a reasonable departure time, we'll use
				// that. This is safe to use since we know we don't have
				// self edges (as the constructor disallows such objects).
				// Note that this has to be on the same day according to the
				// specification of this project.
				if (fi.isOnSameDepartureDayAs(departureDate)) {
					listUnsolvedPaths.add(new ItineraryBuilder(fi));
				}
			}
		}
	}

	/**
	 * Since the unsolved paths may -- after the first search before the loop
	 * phase of permutation generation -- be already complete, this will safely
	 * handle those cases and remove them to the proper solved list.
	 * 
	 * @param listUnsolvedPaths
	 *            The list of unsolved paths.
	 * 
	 * @param listOfFlights
	 *            The complete list of flights that are to be returned from the
	 *            search.
	 * 
	 * @param destination
	 *            The name of the final destination.
	 */
	private void migrateCompleteItineraries(
			LinkedList<ItineraryBuilder> listUnsolvedPaths,
			List<List<FlightInformation>> listOfFlights, String destination) {
		assert listOfFlights != null;
		assert listUnsolvedPaths != null;
		assert destination != null;

		// This will contain the solved paths, if any.
		LinkedList<ItineraryBuilder> solvedPaths = new LinkedList<>();

		// Collect the solved paths into a new list.
		for (ItineraryBuilder li : listUnsolvedPaths) {
			if (li.getLastDestination().equals(destination)) {
				solvedPaths.add(li);
			}
		}

		// Remove them from the unsolved lists path. This gets around the
		// annoying concurrent modification error that would happen otherwise.
		listUnsolvedPaths.removeAll(solvedPaths);

		// Add the solved paths to their proper place.
		for (ItineraryBuilder solvedItList : solvedPaths) {
			listOfFlights.add(solvedItList.getList());
		}
	}

	/**
	 * Takes a single unsolved itinerary list, looks at the end node, and will
	 * check (from the arrival time at the final destination of this list) all
	 * the other possible valid times. It does this by traversing the graph and
	 * marking the nodes we cannot visit, then only visiting the valid nodes and
	 * taking all the different flight information travel times that are valid.
	 * 
	 * @param unsolvedItinerary
	 *            A valid permutation of flight information to expand outwards
	 *            from.
	 * 
	 * @return A list of all the valid flight informations that permutate from
	 *         the last destination which should be further pursued as valid
	 *         paths. This can return empty.
	 */
	private LinkedList<FlightInformation> findFlightPermutations(
			ItineraryBuilder unsolvedItinerary) {
		LinkedList<FlightInformation> flightPerms = new LinkedList<>();

		// Reset the graph to being unmarked, and then mark it based on the
		// positions we have already been to in the given list.
		for (FlightNode node : nameToNode.values()) {
			node.unmarkVisited();
		}
		for (FlightInformation fi : unsolvedItinerary.getList()) {
			assert nameToNode.containsKey(fi.getOrigin());
			nameToNode.get(fi.getOrigin()).markVisited();
		}

		// Since there is overlap (as in, destination matches the origin of
		// the next arrival), we chose to mark all the origins. This means
		// the very last spot to be marked is the destination of the final
		// node since there is no origin to mark it with.
		String lastDestName = unsolvedItinerary.getLastDestination();
		assert nameToNode.containsKey(lastDestName);
		nameToNode.get(lastDestName).markVisited();

		// Now that we have our node, iterate over all the flights out of the
		// destination node and see which ones are worth pursuing.
		FlightNode lastNode = nameToNode.get(lastDestName);
		LocalDateTime lastArrival = unsolvedItinerary.getLastArrivalTime();
		for (String connectedName : lastNode.getFlightData().keySet()) {
			List<FlightInformation> flights = lastNode.getFlightData()
					.get(connectedName);
			// Get the node and see if it's marked (to ignore cycles).
			if (nameToNode.get(connectedName).isNotVisited()) {
				for (FlightInformation flightInfo : flights) {
					if (flightInfo.withinDepartureTime(lastArrival)) {
						flightPerms.add(flightInfo);
					}
				}
			}
		}

		return flightPerms;
	}

	/**
	 * Gets a list of flights on a date that directly go to/from a place.
	 * 
	 * @param departureDate
	 *            The date to search.
	 * 
	 * @param origin
	 *            The origin place.
	 * 
	 * @param destination
	 *            The destination place.
	 * 
	 * @return A list of flights from the origin to the destination at the
	 *         specified time from this graph.
	 */
	public List<FlightInformation> getFlightsFromDate(LocalDate departureDate,
			String origin, String destination) {
		checkNotNull(departureDate);
		checkNotNull(origin);
		checkNotNull(departureDate);
		
		List<FlightInformation> listFlights = new LinkedList<>();

		// If it's to and from the same place, return an empty list since that
		// is not worth searching (specified to be pointless by instructors).
		if (origin.equals(destination)) {
			return listFlights;
		}

		// Go through all the flights and find the ones on the date.
		if (nameToNode.containsKey(origin)) {
			List<FlightInformation> flightInfo = nameToNode.get(origin)
					.getFlightData().get(destination);
			for (FlightInformation fi : flightInfo) {
				// If it's on the same departure day, then remember this one.
				if (fi.isOnSameDepartureDayAs(departureDate)) {
					listFlights.add(fi);
				}
			}
		}

		return listFlights;
	}

	/**
	 * Gets the number of loaded nodes in this graph.
	 * 
	 * @return Number of loaded nodes in this graph.
	 */
	public int getNumberOfNodes() {
		return nameToNode.size();
	}
	
	/**
	 * Gets the flight nodes for this object.
	 * 
	 * @return
	 * 		The flight nodes for this object.
	 */
	public Collection<FlightNode> getFlightNodes() {
		return nameToNode.values();
	}
}
