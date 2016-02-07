package airline.flight;

import static util.Assertions.*;

import org.joda.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Maintains an itinerary list that is a single permutation of a path that may
 * possibly go to a desired destination. The goal is to be able to constantly
 * build an itinerary until it is complete.
 * 
 * @author Chris
 */
public class ItineraryBuilder {

	/**
	 * The list of flights after the origin node.
	 */
	private LinkedList<FlightInformation> listFlightInfo;

	/**
	 * Creates a new itinerary list from a single flight info.
	 * 
	 * @param flightInfo
	 *            The starting flight information.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public ItineraryBuilder(FlightInformation flightInfo) {
		checkNotNull(flightInfo);
		listFlightInfo = new LinkedList<>();
		listFlightInfo.add(flightInfo);
	}

	/**
	 * Copies the itinerary list to make a clone. The references are copied
	 * over, so the new list can be added to or mutated without affecting the
	 * original. The references inside the list that are copied however... are
	 * still the same (this is not a full deep copy).
	 * 
	 * @param itineraryList
	 *            The itinerary list to copy.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public ItineraryBuilder(ItineraryBuilder itineraryList) {
		checkNotNull(itineraryList);
		listFlightInfo = new LinkedList<>(itineraryList.listFlightInfo);
	}

	/**
	 * Adds flight information to the end of the list.
	 * 
	 * @param flightInfo
	 *            The flight information to add.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public void add(FlightInformation flightInfo) {
		checkNotNull(flightInfo);
		listFlightInfo.add(flightInfo);
	}

	/**
	 * Gets the last destination on the list of flights.
	 * 
	 * @return This last destination name.
	 */
	public String getLastDestination() {
		return listFlightInfo.getLast().getDestination();
	}

	/**
	 * Gets the time this plane will land at the end of its journey.
	 * 
	 * @return This date time of when the plane will arrive after all the trips.
	 */
	public LocalDateTime getLastArrivalTime() {
		return listFlightInfo.getLast().getArrivalDateTime();
	}

	/**
	 * Gets the last node in the list.
	 * 
	 * @return The last node in this list.
	 */
	public FlightInformation getLast() {
		return listFlightInfo.getLast();
	}

	/**
	 * Returns a new list of the data.
	 * 
	 * @return A new list of this data.
	 */
	public List<FlightInformation> getList() {
		return new LinkedList<>(listFlightInfo);
	}
}
