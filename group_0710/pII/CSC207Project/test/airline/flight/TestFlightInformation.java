package airline.flight;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * Tests the flight information class.
 * 
 * @author Chris
 */
public class TestFlightInformation {

	/**
	 * A constant earlier time than the 'later' field.
	 */
	private static LocalDateTime early = LocalDateTime.of(2015, 1, 5, 1, 6);

	/**
	 * A constant later time than the 'early' field.
	 */
	private static LocalDateTime later = LocalDateTime.of(2015, 1, 5, 2, 7);

	/**
	 * Tests valid constructors with getters/setters.
	 */
	@Test
	public void testConstructorsAndGetters() {
		FlightInformation fi = new FlightInformation("5", early, later,
				"airline", "origin", "destination", 399.99);
		assertEquals(fi.getFlightNumber(), "5");
		assertEquals(fi.getDepartureDateTime(), early);
		assertEquals(fi.getArrivalDateTime(), later);
		assertEquals(fi.getAirline(), "airline");
		assertEquals(fi.getOrigin(), "origin");
		assertEquals(fi.getDestination(), "destination");
		assertEquals(fi.getCost(), 399.99, 0.001);
		assertEquals(fi.getTravelTimeMinutes(), 61);

		fi = new FlightInformation("5s", "2015-01-05 01:06", "2015-01-05 02:07",
				"airline", "origin", "destination", 399.99);
		assertTrue(fi.getDepartureDateTime().compareTo(early) == 0);
		assertTrue(fi.getArrivalDateTime().compareTo(later) == 0);
	}

	/**
	 * Tests the departure time boundaries.
	 */
	@Test
	public void testWithinDepartureTime() {
		LocalDateTime early = LocalDateTime.of(2015, 1, 5, 12, 6);
		LocalDateTime after = LocalDateTime.of(2015, 1, 5, 12, 7);
		LocalDateTime arrive = LocalDateTime.of(2015, 1, 5, 11, 6);
		LocalDateTime tooEarly = LocalDateTime.of(2015, 1, 5, 6, 5);

		FlightInformation fi = new FlightInformation("5", early, after,
				"airline", "origin", "destination", 399.99);
		assertTrue(fi.withinDepartureTime(arrive));
		assertFalse(fi.withinDepartureTime(tooEarly));
	}

	/**
	 * Tests invalid args, like negatives.
	 */
	@Test
	public void testInvalidArgs() {
		// Can't arrive before the departure.
		try {
			new FlightInformation("1", later, early, "a", "o", "d", 399.99);
			fail();
		} catch (IllegalArgumentException e) {
		}

		// Positive money.
		try {
			new FlightInformation("1", later, early, "a", "o", "d", -0.01);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Tests null arguments.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullConstructors1() {
		new FlightInformation("4", null, later, "a", "o", "d", 399.99);
	}

	/**
	 * Tests null arguments.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullConstructors2() {
		new FlightInformation("4", early, null, "a", "o", "d", 399.99);
	}

	/**
	 * Tests null arguments.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullConstructors3() {
		new FlightInformation("4", early, later, null, "o", "d", 399.99);
	}

	/**
	 * Tests null arguments.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullConstructors4() {
		new FlightInformation("4", early, later, "a", null, "d", 399.99);
	}

	/**
	 * Tests null arguments.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullConstructors5() {
		new FlightInformation("4", early, later, "a", "o", null, 399.99);
	}

	/**
	 * Tests to string.
	 */
	@Test
	public void testToString() {
		String s = new FlightInformation("4", early, later, "someAirline",
				"origin", "dest", 399.99).toString();
		String expected = "4,2015-01-05 01:06,2015-01-05 02:07,someAirline"
				+ ",origin,dest,399.99";
		assertEquals(expected, s);
	}
}
