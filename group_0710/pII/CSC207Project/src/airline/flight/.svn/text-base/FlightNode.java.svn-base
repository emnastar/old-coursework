package airline.flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.Assertions.*;

/**
 * A container for all the possible flights from this node elsewhere.
 * 
 * @author Chris
 */
public class FlightNode {

	/**
	 * The name of this node.
	 */
	private String name;

	/**
	 * Contains a map -> map -> list of information relationship where the
	 * flight data for this location to another destination can be looked up by
	 * name.
	 */
	private HashMap<String, List<FlightInformation>> flightData;

	/**
	 * If this node has been visited when traversing the graph.
	 */
	private boolean visited;

	/**
	 * Creates a new flight node from the provided name.
	 * 
	 * @param name
	 *            The name of the node.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 */
	public FlightNode(String name) {
		checkNotNull(name);
		this.name = name;
		flightData = new HashMap<>();
	}

	/**
	 * Adds a node connection via flight information.
	 * 
	 * @param flightInfo
	 *            The flight information to add.
	 * 
	 * @throws NullPointerException
	 *             If either argument is null.
	 */
	public void addConnection(FlightInformation flightInfo) {
		checkNotNull(flightInfo);
		checkArgument(flightInfo.getOrigin().equals(this.name));

		// Add an empty list if this doesn't exist.
		String destName = flightInfo.getDestination();
		if (!flightData.containsKey(destName)) {
			flightData.put(destName, new ArrayList<>());
		}

		flightData.get(destName).add(flightInfo);
	}

	/**
	 * Sets this node to have been visited.
	 */
	public void markVisited() {
		visited = true;
	}

	/**
	 * Sets this node to not have been visited.
	 */
	public void unmarkVisited() {
		visited = false;
	}

	/**
	 * Checks If this node was visited already or not.
	 * 
	 * @return True if not visited, false if visited.
	 */
	public boolean isNotVisited() {
		return !visited;
	}

	/**
	 * Gets the name of the node.
	 * 
	 * @return This name of the node.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the flight data.
	 * 
	 * @return This flight data.
	 */
	public HashMap<String, List<FlightInformation>> getFlightData() {
		return flightData;
	}
}
