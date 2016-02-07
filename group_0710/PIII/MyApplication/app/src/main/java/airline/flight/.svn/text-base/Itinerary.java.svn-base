package airline.flight;

import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import java.util.LinkedList;
import java.util.List;

import static util.Assertions.*;

/**
 * Represents a completed itinerary.
 * 
 * @author Chris
 */
public class Itinerary {

	/**
	 * The list of all the flights.
	 */
	private LinkedList<FlightInformation> listOfFlights;

	/**
	 * The travel time in minutes for the entire itinerary.
	 */
	private int travelTimeMinutes;

	/**
	 * The total cost for the entire itinerary.
	 */
	private double totalCost;

	/**
	 * Creates an itinerary from a completed list.
	 * 
	 * @param flightList
	 *            A list of flight informations which resemble a complete set of
	 *            connecting flights.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public Itinerary(List<FlightInformation> flightList) {
		checkNotNull(flightList);

		listOfFlights = new LinkedList<>();
		travelTimeMinutes = 0;
		totalCost = 0;

		// Add the flights and track the cost/duration.
		for (FlightInformation fi : flightList) {
			listOfFlights.add(fi);
			travelTimeMinutes += fi.getTravelTimeMinutes();
			totalCost += fi.getCost();
		}
	}

	/**
	 * Gets the total travel time (which includes waiting for other flights).
	 * This is equal to comparing the departure time of the first flight and the
	 * arrival time of the last flight.
	 * 
	 * @return This total time consumed by all flights and waiting between
	 *         places.
	 */
	public int totalTravelTimeMinutes() {
		LocalDateTime start = listOfFlights.getFirst().getDepartureDateTime();
		LocalDateTime end = listOfFlights.getLast().getArrivalDateTime();
		return Minutes.minutesBetween(start, end).getMinutes();
	}

	/**
	 * Converts a list of itinerary information into an actual list of
	 * itineraries.
	 * 
	 * @param flightData
	 *            The list of 'list of flight data' to convert into a list of
	 *            itinerary objects.
	 * 
	 * @return A list of converted flight itineraries.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public static List<Itinerary> convertListToItineraryList(
			List<List<FlightInformation>> flightData) {
		checkNotNull(flightData);

		List<Itinerary> list = new LinkedList<>();
		for (List<FlightInformation> flist : flightData) {
			list.add(new Itinerary(flist));
		}
		return list;
	}

	/**
	 * Gets the total travel time in minutes for this itinerary. This is only
	 * for airborne minutes.
	 * 
	 * @return This total travel time in minutes for this itinerary. Note that
	 *         this does not include the wait between flights, this is purely
	 *         air travel time.
	 */
	public int getTravelTimeMinutes() {
		return travelTimeMinutes;
	}

	/**
	 * Gets the total cost for this itinerary.
	 * 
	 * @return The total cost for this itinerary.
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Gets a copied list of references for all the flights in this itinerary.
	 * 
	 * @return A copied list of references for all the flights in this
	 *         itinerary.
	 */
	public LinkedList<FlightInformation> getListOfFlights() {
		return new LinkedList<>(listOfFlights);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String out = "";
		for (FlightInformation fi : listOfFlights) {
			out += fi.toString() + "\n";
		}
		if (out.length() > 0) {
			out.substring(0, out.length() - 1);
		}
		return out;
	}

	/**
	 * Creates a String that is outputted so the Driver tests pass.
	 * 
	 * @return A string result without the costs and summed up at the end.
	 */
	public String toStringCostTimeNewline() {
		String out = "";
		for (FlightInformation fi : listOfFlights) {
			out += fi.toStringNoCost() + "\n";
		}
		out += String.format("%.2f\n", getTotalCost());
		int totalTravelMinutes = totalTravelTimeMinutes();
		int hours = totalTravelMinutes / 60;
		int minutes = totalTravelMinutes % 60;
		out += String.format("%02d:%02d", hours, minutes);
		return out;
	}
}
