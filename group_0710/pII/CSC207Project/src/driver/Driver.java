package driver;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import airline.Database;
import airline.flight.FlightInformation;
import airline.flight.Itinerary;

/** A Driver used for autotesting the project backend. */
public class Driver {

	/**
	 * Contains the information for the database that will be used through the
	 * driver.
	 */
	private static Database database = new Database();

	/**
	 * Uploads client information to the application from the file at the given
	 * path.
	 * 
	 * @param path
	 *            the path to an input csv file of client information with lines
	 *            in the format:
	 *            LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
	 *            (the ExpiryDate is stored in the format YYYY-MM-DD)
	 */
	public static void uploadClientInfo(String path) {
		// Shouldn't happen, but just to be safe...
		if (path == null) {
			return;
		}

		try {
			database.loadClientData(path);
		} catch (IOException e) {
			System.err.println(
					"Warning: Driver gave an invalid path or had an IO error.");
		}
	}

	/**
	 * Uploads flight information to the application from the file at the given
	 * path.
	 * 
	 * @param path
	 *            the path to an input csv file of flight information with lines
	 *            in the format:
	 *            Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *            Destination,Price (the dates are in the format YYYY-MM-DD; the
	 *            price has exactly two decimal places)
	 */
	public static void uploadFlightInfo(String path) {
		// Shouldn't happen...
		if (path == null) {
			return;
		}

		try {
			database.loadFlightData(path);
		} catch (IOException e) {
			System.err.println(
					"Warning: Driver gave an invalid path or had an IO error.");
		}
	}

	/**
	 * Returns the information stored for the client with the given email.
	 * 
	 * @param email
	 *            the email address of a client
	 * @return the information stored for the client with the given email in
	 *         this format:
	 *         LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
	 *         (the ExpiryDate is stored in the format YYYY-MM-DD)
	 */
	public static String getClient(String email) {
		// Don't allow bad calls.
		if (email == null) {
			return "";
		}
		
		return database.getClient(email).orElse("");
	}

	/**
	 * Returns all flights that depart from origin and arrive at destination on
	 * the given date.
	 * 
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight origin
	 * @param destination
	 *            a flight destination
	 * @return the flights that depart from origin and arrive at destination on
	 *         the given date formatted with one flight per line in exactly this
	 *         format: Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *         Destination,Price (the dates are in the format YYYY-MM-DD; the
	 *         price has exactly two decimal places)
	 */
	public static String getFlights(String date, String origin,
			String destination) {
		// Don't allow bad calls.
		if (date == null || origin == null || destination == null) {
			return "";
		}
		
		// Convert to a LocalDate.
		String[] tokens = date.split("-");
		int year = Integer.parseInt(tokens[0]);
		int month = Integer.parseInt(tokens[1]);
		int day = Integer.parseInt(tokens[2]);
		LocalDate localDate = LocalDate.of(year, month, day);

		// Get all the flights and create one flight per line.
		String out = "";
		try {
			List<FlightInformation> flights = database.getFlightsFromDate(localDate,
					origin, destination);
			for (FlightInformation fi : flights) {
				out += fi.toString() + "\n";
			}
		} catch (IllegalArgumentException | DateTimeException e) {
			return ""; // Corrupt data entered, this cannot be valid.
		}

		// If the list of flights is empty, just return that.
		if (out.length() == 0) {
			return out;
		}

		return out.substring(0, out.length() - 1); // Trim the '\n'.
	}

	/**
	 * Returns all itineraries that depart from origin and arrive at destination
	 * on the given date. If an itinerary contains two consecutive flights F1
	 * and F2, then the destination of F1 should match the origin of F2. To
	 * simplify our task, if there are more than 6 hours between the arrival of
	 * F1 and the departure of F2, then we do not consider this sequence for a
	 * possible itinerary (we judge that the stopover is too long).
	 * 
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight original
	 * @param destination
	 *            a flight destination
	 * @return itineraries that depart from origin and arrive at destination on
	 *         the given date with stopovers at or under 6 hours. Each itinerary
	 *         in the output should contain one line per flight, in the format:
	 *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *         Destination followed by total price (on its own line, exactly two
	 *         decimal places), followed by total duration (on its own line, in
	 *         format HH:MM).
	 */
	public static String getItineraries(String date, String origin,
			String destination) {
		// Don't allow bad calls.
		if (date == null || origin == null || destination == null) {
			return "";
		}
		
		String output = "";
		try {
			for (Itinerary i : database.searchItineraries(origin, destination,
					date)) {
				output += i.toStringCostTimeNewline() + "\n";
			}
		} catch (IllegalArgumentException | DateTimeException e) {
			output = ""; // Corrupt data entered, this cannot be valid.
		}
		
		return output;
	}

	/**
	 * Returns the same itineraries as getItineraries produces, but sorted
	 * according to total itinerary cost, in non-decreasing order.
	 * 
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight original
	 * @param destination
	 *            a flight destination
	 * @return itineraries (sorted in non-decreasing order of total itinerary
	 *         cost) that depart from origin and arrive at destination on the
	 *         given date with stopovers at or under 6 hours. Each itinerary in
	 *         the output should contain one line per flight, in the format:
	 *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *         Destination followed by total price (on its own line, exactly two
	 *         decimal places), followed by total duration (on its own line, in
	 *         format HH:MM).
	 */
	public static String getItinerariesSortedByCost(String date, String origin,
			String destination) {
		// Don't allow bad calls.
		if (date == null || origin == null || destination == null) {
			return "";
		}
				
		String output = "";
		try {
			for (Itinerary i : database.searchItineriariesByCost(origin,
					destination, date)) {
				output += i.toStringCostTimeNewline() + "\n";
			}
		} catch (IllegalArgumentException | DateTimeException e) {
			output = ""; // Corrupt data entered, this cannot be valid.
		}
		return output;
	}

	/**
	 * Returns the same itineraries as getItineraries produces, but sorted
	 * according to total itinerary travel time, in non-decreasing order.
	 * 
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight original
	 * @param destination
	 *            a flight destination
	 * @return itineraries (sorted in non-decreasing order of travel itinerary
	 *         travel time) that depart from origin and arrive at destination on
	 *         the given date with stopovers at or under 6 hours. Each itinerary
	 *         in the output should contain one line per flight, in the format:
	 *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *         Destination followed by total price (on its own line, exactly two
	 *         decimal places), followed by total duration (on its own line, in
	 *         format HH:MM).
	 */
	public static String getItinerariesSortedByTime(String date, String origin,
			String destination) {
		// Don't allow bad calls.
		if (date == null || origin == null || destination == null) {
			return "";
		}
				
		String output = "";
		try {
			for (Itinerary i : database.searchItineriariesByTime(origin,
					destination, date)) {
				output += i.toStringCostTimeNewline() + "\n";
			}
		} catch (IllegalArgumentException | DateTimeException e) {
			output = ""; // Corrupt data entered, this cannot be valid.
		}
		return output;
	}
}
