package airline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import airline.flight.FlightGraph;
import airline.flight.FlightInformation;
import airline.flight.FlightNode;
import airline.flight.Itinerary;
import user.Client;
import util.Constants;
import parser.FlightInfoParser;

import static util.Assertions.*;

/**
 * The main starting object that will handle all the backend transactions.
 * 
 * @author Chris, Mena, Li Ju, David
 */
public class Database {

	/**
	 * The flight graph with all the information.
	 */
	private FlightGraph flightGraph;

	/**
	 * A map which contains all Clients Use email as key, and Client object as
	 * value.
	 */
	private Map<String, Client> loadedClients;

	/**
	 * Creates an empty database.
	 */
	public Database() {
		flightGraph = new FlightGraph();
		loadedClients = new HashMap<>();
	}

	/**
	 * Takes a flight information csv path, parses it, and adds its content to
	 * flightGraph.
	 * 
	 * @param path
	 *            The file path of flight information csv file.
	 * 
	 * @throws IOException
	 *             If the path has any IO errors when reading or the file isn't
	 *             found.
	 */
	public void loadFlightData(String path) throws IOException {
		FlightInfoParser flightInfoParser = new FlightInfoParser(path);
		List<FlightInformation> flightInfo = flightInfoParser.getFlightInfo();

		for (FlightInformation flightEntry : flightInfo) {
			flightGraph.addFlight(flightEntry);
		}
	}

	/**
	 * Gets all clients from the given csv file
	 * 
	 * @param path
	 *            The path of the csv file.
	 * 
	 * @throws IOException
	 *             If any error occurs while reading in the client data.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public void loadClientData(String path) throws IOException {
		checkNotNull(path);
		BufferedReader fileReader = null;
		String line = "";
		fileReader = new BufferedReader(new FileReader(path));
		while ((line = fileReader.readLine()) != null) {
			String[] tokens = line.split(Constants.DELIMITER);
			if (tokens.length > 0) {
				Client newClient = new Client(tokens[0], tokens[1], tokens[2],
						tokens[3], tokens[4], tokens[5]);
				loadedClients.put(tokens[2], newClient);
			}
		}
		fileReader.close();
	}

	/**
	 * Adds the client's info into a given csv file.
	 * 
	 * @param path
	 *            The path to the csv file.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public void addClientData(String path) {
		checkNotNull(path);
		Collection<Client> allClients = loadedClients.values();
		for (Client singleClient : allClients) {
			singleClient.addToCsv(path);
		}
	}

	/**
	 * Searches for itineraries based on the origin/destination/date.
	 * 
	 * @param origin
	 *            The origin to search from.
	 * 
	 * @param destination
	 *            The destination to arrive at.
	 * 
	 * @param departureDate
	 *            The desired date to leave on.
	 * 
	 * @return A list of all itineraries that match the provided arguments.
	 * 
	 * @throws NullPointerException
	 *             If any arguments are null.
	 * 
	 * @throws IllegalArgumentException
	 *             If the departure date is not in YYYY-MM-DD format.
	 * 
	 * @throws DateTimeException
	 *             If the date time could not be parsed properly.
	 */
	public List<Itinerary> searchItineraries(String origin, String destination,
			String departureDate) {
		checkNotNull(origin);
		checkNotNull(destination);
		checkNotNull(departureDate);
		return flightGraph.searchForItineraries(departureDate, origin,
				destination);
	}

	/**
	 * @author David Witka
	 * 
	 *         Takes a list, and compares the cost of different itineraries.
	 * 
	 * @param origin
	 *            The origin of the flight.
	 * @param destination
	 *            The destination of the flight.
	 * @param departureDate
	 *            Date of departure.
	 * 
	 * @return Sorted list of itineraries based on cost.
	 * 
	 * @throws NullPointerException
	 *             If any arguments are null.
	 */
	public List<Itinerary> searchItineriariesByCost(String origin,
			String destination, String departureDate) {
		checkNotNull(origin);
		checkNotNull(destination);
		checkNotNull(departureDate);
		List<Itinerary> unsortedList = searchItineraries(origin, destination,
				departureDate);
		Collections.sort(unsortedList, new Comparator<Itinerary>() {
			public int compare(Itinerary one, Itinerary other) {
				Double T1 = one.getTotalCost();
				Double T2 = other.getTotalCost();
				return T1.compareTo(T2);
			}
		});
		List<Itinerary> sortedList = unsortedList;
		return sortedList; // Return a sorted list.
	}

	/**
	 * @author David Witka
	 * 
	 *         Takes a list, and compares the flight time of different
	 *         itineraries.
	 * 
	 * @param origin
	 *            The origin of the flight.
	 * @param destination
	 *            The destination of the flight.
	 * @param departureDate
	 *            Date of departure.
	 * 
	 * @return Sorted list of itineraries based on flight time.
	 * 
	 * @throws NullPointerException
	 *             If any argument is null.
	 * 
	 * @throws IllegalArgumentException
	 *             If the departure date is not in YYYY-MM-DD format.
	 * 
	 * @throws DateTimeException
	 *             If the date time could not be parsed properly.
	 */
	public List<Itinerary> searchItineriariesByTime(String origin,
			String destination, String departureDate) {
		checkNotNull(origin);
		checkNotNull(destination);
		checkNotNull(departureDate);
		List<Itinerary> unsortedList = searchItineraries(origin, destination,
				departureDate);
		Collections.sort(unsortedList, new Comparator<Itinerary>() {
			public int compare(Itinerary one, Itinerary other) {
				Integer T1 = one.totalTravelTimeMinutes();
				Integer T2 = other.totalTravelTimeMinutes();
				return T1.compareTo(T2);
			}
		});
		List<Itinerary> sortedList = unsortedList;
		return sortedList; // Return a sorted list.
	}

	/**
	 * Searches for the flights from a given date that go directly to the
	 * location.
	 * 
	 * @param departureDate
	 *            The date to depart on.
	 * 
	 * @param origin
	 *            The origin location.
	 * 
	 * @param destination
	 *            The desired destination.
	 * 
	 * @return A list of flights that go to the specified desired location on
	 *         the provided date from the origin.
	 * 
	 * @throws NullPointerException
	 *             If any argument is null.
	 *             
	 * @throws IllegalArgumentException
	 *             If the departure date is not in YYYY-MM-DD format.
	 * 
	 * @throws DateTimeException
	 *             If the date time could not be parsed properly.
	 */
	public List<FlightInformation> getFlightsFromDate(LocalDate departureDate,
			String origin, String destination) {
		checkNotNull(origin);
		checkNotNull(destination);
		checkNotNull(departureDate);
		return flightGraph.getFlightsFromDate(departureDate, origin,
				destination);
	}

	/**
	 * Gets the client by email.
	 * 
	 * @param email
	 *            The email of the client.
	 * 
	 * @return Client information as an optional value (since the email may not
	 *         be in this database).
	 * 
	 * @throws NullPointerException
	 *             If the email is null.
	 */
	public Optional<String> getClient(String email) {
		checkNotNull(email);
		if (!loadedClients.containsKey(email))
			return Optional.empty();
		return Optional.of(loadedClients.get(email).toString());
	}

	/**
	 * Writes all the flight information to a file.
	 * 
	 * @param path
	 *            The file to write to.
	 * 
	 * @throws IOException
	 *             If any IO error occurs.
	 * 
	 * @throws NullPointerException
	 *             If the path is null.
	 */
	public void writeFlightsToFile(String path) throws IOException {
		checkNotNull(path);

		try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
			for (FlightNode fn : flightGraph.getFlightNodes()) {
				for (List<FlightInformation> lf : fn.getFlightData().values()) {
					for (FlightInformation fi : lf) {
						br.write(fi.toString() + "\n");
					}
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Writes all the client information to a file.
	 * 
	 * @param path
	 *            The file to write to.
	 * 
	 * @throws IOException
	 *             If any IO error occurs.
	 * 
	 * @throws NullPointerException
	 *             If the path is null.
	 */
	public void writeClientsToFile(String path) throws IOException {
		checkNotNull(path);

		try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
			for (Client client : loadedClients.values()) {
				br.write(client.toString() + "\n");
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Returns a collection of all the loaded clients.
	 * 
	 * @return All the loaded clients.
	 */
	public Collection<Client> getAllClients() {
		return loadedClients.values();
	}

	/**
	 * Used in debugging with test cases.
	 * 
	 * @return Number of nodes loaded into this graph.
	 */
	int getNumberOfNodes() {
		return flightGraph.getNumberOfNodes();
	}
}
