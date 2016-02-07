package util;

import java.util.List;
import java.util.Set;

/**
 * A linked list which is backed by a hashmap or hashset, which means that the
 * data inside can be looked up in amortized O(1) time, yet allowing for a list
 * form to be added to the tail in O(1) time.
 * 
 * @author Chris
 *
 * @param <T>
 *            The data to hold inside the list.
 */
public interface HashedLinkedList<T> {

	/**
	 * Adds an object to the tail of the list.
	 * 
	 * @param t
	 *            The object to add.
	 * 
	 * @throws NullPointerException
	 *             If the argument is null.
	 * 
	 * @throws IllegalArgumentException
	 *             If the argument is already in the list. Each element must be
	 *             unique or else it cannot be added.
	 */
	void addTail(T t);

	/**
	 * Checks if the element is contained in the list in O(1) amortized time.
	 * 
	 * @param t
	 *            The object to check.
	 * 
	 * @return True if this object exists, false if it is null or is not
	 *         contained in this data structure.
	 */
	boolean contains(T t);

	/**
	 * Gets how many nodes are in the linked list.
	 * 
	 * @return The number of nodes in this list.
	 */
	int size();

	/**
	 * Gets the backing set of nodes.
	 * 
	 * @return This backing set of nodes.
	 */
	Set<T> getSet();

	/**
	 * Gets the backing list of nodes.
	 * 
	 * @return This backing list of nodes.
	 */
	List<T> getList();
}
