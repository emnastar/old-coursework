package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import airline.flight.FlightInformation;

/**
 * A parser for parsing flight information
 * 
 * @author Mena
 *
 */
public class FlightInfoParser extends CsvParser {
	List<FlightInformation> flightInfo; // stores flight info

	/**
	 * parses csv path and creates a corresponding FlightInformation class
	 * 
	 * @param path
	 *            String of the location of the csv file.
	 * 
	 * @throws IOException
	 *             If the path is wrong or there is any IO error.
	 */
	public FlightInfoParser(String path) throws IOException {
		super(path);
		this.flightInfo = new ArrayList<FlightInformation>();
		this.infoToFlightInfo(super.getInfo(), this.flightInfo);
	}

	/**
	 * returns the List of FlightInformation
	 * 
	 * @return FlightInfo List of FlightInformation obtained by parsing csv path
	 */
	public List<FlightInformation> getFlightInfo() {
		return flightInfo;
	}

	/**
	 * Takes info and creates the corresponding List of FlightInformation
	 * 
	 * @param info
	 *            a list of String[] parsed from the csv file
	 * @param flightInfo
	 *            a list of FlightInformation corresponding to info
	 */
	private void infoToFlightInfo(List<String[]> info,
			List<FlightInformation> flightInfo) {
		for (String[] entry : info) {
			// new FlightInformation class per entry
			FlightInformation current;

			// Assign corresponding variables
			// Change String to the right class
			String flightNumber = entry[0];
			String departureTime = entry[1];
			String arrivalTime = entry[2];
			String airline = entry[3];
			String origin = entry[4];
			String destination = entry[5];
			double cost = Double.parseDouble(entry[6]);

			// Construct
			current = new FlightInformation(flightNumber, departureTime,
					arrivalTime, airline, origin, destination, cost);
			// add to list
			flightInfo.add(current);
		}
	}

}
