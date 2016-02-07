package airline.flight;

import static org.junit.Assert.*;

import java.io.IOException;
import org.joda.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import parser.FlightInfoParser;

/**
 * Tests that the itinerary lists works.
 * 
 * @author Chris
 */
public class TestItineraryBuilder {

	/**
	 * The parsed flight info.
	 */
	private static List<FlightInformation> flightData;
	
	/**
	 * Tells other tests to fail if this is false.
	 */
	private static boolean textFileLoaded;
	
	/**
	 * The loaded data in an itinerary list format.
	 */
	private static ItineraryBuilder itList;
	
	/**
	 * Sets up the data.
	 */
	@BeforeClass
	public static void setupClass() {
		try {
			flightData = new FlightInfoParser("test.txt").getFlightInfo();
		} catch (IOException e) {
			textFileLoaded = false;
			return;
		}
		
		itList = new ItineraryBuilder(flightData.get(0));
		itList.add(flightData.get(1));
		itList.add(flightData.get(2));
	}
	
	/**
	 * Tests that the last destination holds.
	 */
	@Test
	public void testLastDestination() {
		if (textFileLoaded) {
			fail("Cannot test, data not properly loaded.");
		}
		
		assertEquals(itList.getLastDestination(), "Venice");
	}
	
	/**
	 * Tests that the arrival time holds.
	 */
	@Test
	public void testLastArrivalTime() {
		if (textFileLoaded) {
			fail("Cannot test, data not properly loaded.");
		}
		
		LocalDateTime ldt = new LocalDateTime(2015, 5, 11, 8, 23);
		assertEquals(itList.getLastArrivalTime(), ldt);
	}
	
	/**
	 * Tests that the data is properly getting added.
	 */
	@Test
	public void testProperDataAdded() {
		List<FlightInformation> fi = itList.getList();
		assertEquals(fi.size(), 3);
		assertEquals(fi.get(0).getOrigin(), "Toronto");
		assertEquals(fi.get(0).getDestination(), "Venice");
		assertEquals(fi.get(1).getOrigin(), "Toronto");
		assertEquals(fi.get(1).getDestination(), "London");
		assertEquals(fi.get(2).getOrigin(), "London");
		assertEquals(fi.get(2).getDestination(), "Venice");
	}
}
