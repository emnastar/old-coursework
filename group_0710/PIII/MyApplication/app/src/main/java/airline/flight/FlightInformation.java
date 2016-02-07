package airline.flight;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import util.Constants;

import static util.Assertions.*;

/**
 * A POJO to contain the flight information between one location and another.
 * The data is not considered sanitized, that is up to the graph to enforce. By
 * sanitization, that means that the data is correct, for example: If the origin
 * does not exist in a graph and never should, the graph should take steps to
 * prevent invalid data that could be in this node from sneaking in.
 * 
 * @author Chris
 */
public class FlightInformation {

    /**
     * The pattern of the formatting.
     */
    private static String formatPattern = "yyyy-MM-dd HH:mm";

	/**
	 * A formatter that takes the String date defined in the handout and makes a
	 * proper local date out of it.
	 */
	private static DateTimeFormatter dtFormatter = DateTimeFormat.forPattern(formatPattern);

	/**
	 * The flight number.
	 */
	private String flightNumber;

	/**
	 * When the flight departs the origin.
	 */
	private LocalDateTime departureDateTime;

	/**
	 * When the flight arrives at the destination.
	 */
	private LocalDateTime arrivalDateTime;

	/**
	 * The name of the airline.
	 */
	private String airline;

	/**
	 * The origin name.
	 */
	private String origin;

	/**
	 * The destination name.
	 */
	private String destination;

	/**
	 * The cost in dollars (decimals are cents).
	 */
	private double cost;

	/**
	 * How many minutes it will take.
	 */
	private int travelTimeMinutes;

	/**
	 * Creates a new travel from raw text data by parsing the date times and
	 * passing it to the other constructor.
	 * 
	 * @param flightNumber
	 *            The flight number.
	 * 
	 * @param departureDateTime
	 *            The departure date time.
	 * 
	 * @param arrivalDateTime
	 *            The arrival date time.
	 * 
	 * @param airline
	 *            The airline name.
	 * 
	 * @param origin
	 *            The origin location name.
	 * 
	 * @param destination
	 *            The destination name.
	 * 
	 * @param cost
	 *            How much the cost is (dollars).
	 * 
	 * @throws NullPointerException
	 *             If any argument is null.
	 * 
	 * @throws IllegalArgumentException
	 *             If the cost is negative, if the travel time is negative, or
	 *             if the departure date is after the arrival date.
	 */
	public FlightInformation(String flightNumber, String departureDateTime,
			String arrivalDateTime, String airline, String origin,
			String destination, double cost) {
		this(flightNumber, LocalDateTime.parse(departureDateTime, dtFormatter),
				LocalDateTime.parse(arrivalDateTime, dtFormatter), airline,
				origin, destination, cost);
	}

	/**
	 * Creates a new flight information from the data.
	 * 
	 * @param flightNumber
	 *            The flight number.
	 * 
	 * @param departureDateTime
	 *            The departure date time.
	 * 
	 * @param arrivalDateTime
	 *            The arrival date time.
	 * 
	 * @param airline
	 *            The airline name.
	 * 
	 * @param origin
	 *            The origin location name.
	 * 
	 * @param destination
	 *            The destination name.
	 * 
	 * @param cost
	 *            How much the cost is (dollars).
	 * 
	 * @throws NullPointerException
	 *             If any argument is null.
	 * 
	 * @throws IllegalArgumentException
	 *             If the cost is negative, if the travel time is negative, or
	 *             if the departure date is after the arrival date, or if the
	 *             destination equals the origin.
	 */
	public FlightInformation(String flightNumber,
			LocalDateTime departureDateTime, LocalDateTime arrivalDateTime,
			String airline, String origin, String destination, double cost) {
		checkNotNull(departureDateTime);
		checkNotNull(arrivalDateTime);
		checkNotNull(airline);
		checkNotNull(origin);
		checkNotNull(destination);
		checkArgument(cost >= 0);
		checkArgument(departureDateTime.isBefore(arrivalDateTime));
		checkArgument(!origin.equals(destination));

		this.flightNumber = flightNumber;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.airline = airline;
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
		this.travelTimeMinutes = Minutes.minutesBetween(departureDateTime,
                arrivalDateTime).getMinutes();
	}

	/**
	 * Checks if the flight info provided is within some constant time as
	 * specified in Constants.java for this.
	 * 
	 * @param prevArrivalTime
	 *            The time of the previous arrival.
	 * 
	 * @return True if it's within the gap for the previous arrival time, or
	 *         false otherwise.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public boolean withinDepartureTime(LocalDateTime prevArrivalTime) {
		checkNotNull(prevArrivalTime);
        long diff = Minutes.minutesBetween(prevArrivalTime, departureDateTime).getMinutes();
		return diff >= 0 && diff <= Constants.MAX_MINUTES_PER_FLIGHT_GAP;
	}

	/**
	 * Checks if the provided date is the same day as the departure day.
	 * 
	 * @param date
	 *            The date to check if this departure date is on the same day as
	 *            what's provided.
	 * 
	 * @return True if this flight information is on the same day, false
	 *         otherwise.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public boolean isOnSameDepartureDayAs(LocalDate date) {
		checkNotNull(date);
		int year = departureDateTime.getYear();
		int month = departureDateTime.getMonthOfYear();
		int day = departureDateTime.getDayOfMonth();
		return (year == date.getYear() && month == date.getMonthOfYear()
				&& day == date.getDayOfMonth());
	}

	/**
	 * Gets the flight number.
	 * 
	 * @return The flight number.
	 */
	public String getFlightNumber() {
		return flightNumber;
	}

	/**
	 * Gets the departure date and time.
	 * 
	 * @return The departure date and time.
	 */
	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * Gets the arrival date and time.
	 * 
	 * @return The arrival date and time.
	 */
	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	/**
	 * Gets the airline name.
	 * 
	 * @return The airline name.
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Gets the origin name.
	 * 
	 * @return The origin name.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Gets the destination name.
	 * 
	 * @return The destination name.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Gets the cost of the flight.
	 * 
	 * @return The cost of the flight.
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Gets the travel time in minutes.
	 * 
	 * @return The travel time in minutes.
	 */
	public int getTravelTimeMinutes() {
		return travelTimeMinutes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String deptStr = departureDateTime.toString(formatPattern);
		String arrivalStr = arrivalDateTime.toString(formatPattern);
		String outputCost = String.format("%.2f", cost);
		return flightNumber + "," + deptStr + "," + arrivalStr + "," + airline
				+ "," + origin + "," + destination + "," + outputCost;
	}

	/**
	 * Converts this to the toString() format but instead it uses the date
	 * without any time (YYYY-MM-DD).
	 * 
	 * @return The string in date only form.
	 */
	public String toStringDateOnly() {
		// Pre-process the data first so it's in a usable format for marking.
		String deptStr = String.format("%d-%02d-%02d",
				departureDateTime.getYear(), departureDateTime.getMonthOfYear(),
				departureDateTime.getDayOfMonth());

		String arrivalStr = String.format("%d-%02d-%02d",
				arrivalDateTime.getYear(), arrivalDateTime.getMonthOfYear(),
				arrivalDateTime.getDayOfMonth());

		String outputCost = String.format("%.2f", cost);

		return flightNumber + "," + deptStr + "," + arrivalStr + "," + airline
				+ "," + origin + "," + destination + "," + outputCost;
	}

	/**
	 * Prints out a String version with no cost.
	 * 
	 * @return A String version with no cost.
	 */
	public String toStringNoCost() {
        String deptStr = departureDateTime.toString(formatPattern);
        String arrivalStr = arrivalDateTime.toString(formatPattern);
		return flightNumber + "," + deptStr + "," + arrivalStr + "," + airline
				+ "," + origin + "," + destination;
	}
}
