package airline.flight;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import airline.Database;

/**
 * An extended test on the database to make sure our code is solid.
 * 
 * @author Chris
 */
public class TestDatabaseExtended {

	/**
	 * The test database to use.
	 */
	private static Database db;
	
	/**
	 * If the data was loaded fine.
	 */
	private static boolean loaded;
	
	/**
	 * Sets up the class.
	 */
	@BeforeClass
	public static void setup() {
		db = new Database();
		try {
			db.loadFlightData("res/biggerTest.txt");
			db.loadClientData("res/extendedClientsTest.txt");
			loaded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests flight sorting.
	 * 
	 * @throws IOException
	 * 		If there is a file error when loading.
	 */
	@Test
	public void testFlightSorting() throws IOException {
		assertTrue(loaded);
		
		String actual = "";
		final StringBuilder sb = new StringBuilder();

		// Get the data.
		for (Itinerary li : db.searchItinerariesByCost("Toronto", "Venice", "2015-06-15")) {
			actual += li.toStringCostTimeNewline() + "\n\n";
		}
		actual += "\n";
		for (Itinerary li : db.searchItinerariesByCost("Toronto", "Venice", "2015-06-15")) {
			actual += li.toStringCostTimeNewline() + "\n\n";
		}
		
		// Load in the file data for the expected.
		fail("TO BE FIXED"); // TODO
//		Files.readAllLines(Paths.get("res/sortedFlights.txt"))
//			.stream()
//			.forEach(s -> sb.append(s + "\n"));
		
		assertEquals(actual, sb.toString());
	}
	
	/**
	 * Tests that the client data is loaded properly.
	 */
	@Test
	public void testClients() {
		assertEquals(db.getClient("first.last@email.ca"),
				"First,Last,first.last@email.ca,12 Street,1234123412341234,2019-01-01");
		assertEquals(db.getClient("jane@email.com"),
				"Jane,Doe,jane@email.com,123 Main Street,1111222233334444,2018-08-24");
		assertEquals(db.getClient("a_b@e.c"),
				"A,B,a_b@e.c,0 S,1234567887654321,2012-12-21");
		assertEquals(db.getClient("richard@email.com"),
				"Richard,Roe,richard@email.com,21 First Lane Way,9999888877776666,2017-10-01");
		assertEquals(db.getAllClients().size(), 4);
	}
}
