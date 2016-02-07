package airline.flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * A simple parser until the CSV is ready.
 * 
 * @author Chris
 */
public class Parser {

	/**
	 * A list of flight information.
	 */
	private LinkedList<FlightInformation> flightInfoList;

	/**
	 * Parses the file from the location.
	 * 
	 * @param file
	 *            The file path to parse.
	 * 
	 * @throws IOException
	 *             If the file isn't found or an IO error occurs.
	 */
	public Parser(String file) throws IOException {
		flightInfoList = new LinkedList<>();
		Files.lines(Paths.get(file)).forEach(this::processLine);
	}

	/**
	 * Processes the line of information.
	 * 
	 * @param line
	 *            The line to process.
	 */
	private void processLine(String line) {
		String[] tokens = line.split(",");
		flightInfoList.add(new FlightInformation(tokens[0], tokens[1],
				tokens[2], tokens[3], tokens[4], tokens[5],
				Double.parseDouble(tokens[6])));
	}

	/**
	 * Gets the information parsed.
	 * 
	 * @return This parsed information in list format.
	 */
	public LinkedList<FlightInformation> getFlightInfoList() {
		return flightInfoList;
	}
}
