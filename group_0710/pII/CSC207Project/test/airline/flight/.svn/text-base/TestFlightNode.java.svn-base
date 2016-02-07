package airline.flight;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import parser.FlightInfoParser;

/**
 * Tests that the flight nodes work as expected.
 * 
 * @author Chris
 */
public class TestFlightNode {
	
	/**
	 * A marker to tell if the data was loaded.
	 */
	private static boolean loadedData;

	/**
	 * Checks the flight information.
	 */
	private static List<FlightInformation> flightInfo;
	
	/**
	 * Loads the data before the class is run.
	 */
	@BeforeClass
	public static void setup() {
		try {
			flightInfo = new FlightInfoParser("res/test.txt").getFlightInfo();
			loadedData = true;
		} catch (Exception e) {
			e.printStackTrace();
			loadedData = false;
		}
	}
	
	/**
	 * Tests that the loaded data works fine.
	 */
	@Test
	public void testDataLoaded() {
		if (!loadedData) {
			fail("Cannot test, data not found.");
		}
		
		FlightNode n = new FlightNode(flightInfo.get(0).getOrigin());
		n.addConnection(flightInfo.get(0));
		n.addConnection(flightInfo.get(1));
		
		FlightInformation a = flightInfo.get(0);
		assertEquals(n.getFlightData().size(), 2);
		assertEquals(n.getFlightData().get(a.getDestination()).get(0), a);
	}
	
	/**
	 * Tests invalid constructor arguments.
	 */
	@Test(expected = NullPointerException.class)
	public void testInvalidConstructor() {
		new FlightNode(null);
	}
	
	/**
	 * Tests addition of a flight information that is not for this node, since
	 * the names of the origin don't match.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConnection() {
		FlightNode n = new FlightNode(flightInfo.get(0).getOrigin());
		n.addConnection(flightInfo.get(2));
	}
	
	/**
	 * Tests a null connection.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullConnection() {
		FlightNode n = new FlightNode(flightInfo.get(0).getOrigin());
		n.addConnection(null);
	}
}
