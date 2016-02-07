package util;

import org.joda.time.LocalDate;

import static util.Assertions.*;

/**
 * A collection of useful constants.
 * 
 * @author Chris, Li Ju
 */
public class Constants {

	/**
	 * The amount of hours between arriving at an airport and the next flight.
	 */
	public static final int MAX_HOURS_PER_FLIGHT_GAP = 6;

	/**
	 * The amount of minutes between arriving at an airport and the next flight.
	 */
	public static final int MAX_MINUTES_PER_FLIGHT_GAP = 
			MAX_HOURS_PER_FLIGHT_GAP * 60;

	/**
	 * The character used to seperate fields in the file.
	 */
	public static final String DELIMITER = ",";

	/**
	 * Parses a date from YYYY-MM-DD to a LocalDate.
	 * 
	 * @param date
	 *            The date to parse.
	 * 
	 * @return A converted date to LocalDate.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 * 
	 * @throws NumberFormatException
	 *             If the date is invalid.
	 */
	public static LocalDate parseDate(String date) {
		checkNotNull(date);
		String[] tokens = date.split("-");
		int year = Integer.parseInt(tokens[0]);
		int month = Integer.parseInt(tokens[1]);
		int day = Integer.parseInt(tokens[2]);
		return new LocalDate(year, month, day);
	}
}
