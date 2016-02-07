package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.Constants;

import static util.Assertions.*;

/**
 * A parser for parsing a csv file
 * 
 * @author Mena
 */
public class CsvParser {

	// instance variable
	// List of csv info in String[] format
	private List<String[]> info;

	/**
	 * Parses the csv file path and stores it in info.
	 * 
	 * @param path
	 *            The path to an input csv file
	 * 
	 * @throws IOException
	 *             If there is any IO error reading files or the path is wrong.
	 * 
	 * @throws NullPointerException
	 *             If the path is null.
	 */
	public CsvParser(String path) throws IOException {
		checkNotNull(path);
		info = new ArrayList<String[]>();
		this.parser(path);
	}

	/**
	 * Returns the parsed information.
	 * 
	 * @return A list of information parsed from this object.
	 */
	public List<String[]> getInfo() {
		return info;
	}

	/**
	 * Takes a path of a csv file and parses its data and stores it in info
	 * 
	 * @param path
	 *            Path of a csv file
	 * 
	 * @throws IOException
	 *             If any IO exception or missing file (from a bad path) occurs.
	 */
	private void parser(String path) throws IOException {
		// initialize variables to be used
		String line = "";
		String splitBy = Constants.DELIMITER;

		List<String[]> result;
		result = new ArrayList<String[]>();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			while ((line = reader.readLine()) != null) {
				String[] current = line.split(splitBy); // array of current info
				result.add(current);
			}
		} catch (IOException e) {
			throw e;
		}
		
		info = result;
	}
}
