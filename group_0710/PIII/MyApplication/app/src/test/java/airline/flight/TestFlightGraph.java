package airline.flight;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.FlightInfoParser;

/**
 * Tests the flight graph.
 * 
 * @author Chris
 */
public class TestFlightGraph {

	/**
	 * Loads the data.
	 * 
	 * @throws IOException
	 *             If any IO error occurs.
	 */
	@Test
	public void testLoading() throws IOException {
		FlightGraph graph = new FlightGraph();
		for (FlightInformation fi : new FlightInfoParser("test.txt").getFlightInfo()) {
			graph.addFlight(fi);
		}
	}

	/**
	 * Tests that the flights work.
	 * 
	 * @throws IOException
	 *             If any IO error occurs.
	 */
	@Test
	public void testFlights() throws IOException {
		FlightGraph graph = new FlightGraph();
        for (FlightInformation fi : new FlightInfoParser("test.txt").getFlightInfo()) {
            graph.addFlight(fi);
        }

		List<Itinerary> searched = graph.searchForItineraries("2015-05-10",
				"Toronto", "Venice");
		assertEquals(3, searched.size());
	}
}
