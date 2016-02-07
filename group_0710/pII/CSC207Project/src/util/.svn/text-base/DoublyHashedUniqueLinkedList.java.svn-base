package util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static util.Assertions.*;

/**
 * A doubly linked list that allows for amortized O(1) lookup of what elements
 * are in the list. Every element should be unique, meaning they can be put
 * in a hash table without collisions.
 * 
 * @author Chris
 */
public class DoublyHashedUniqueLinkedList<T> implements HashedLinkedList<T> {

	/**
	 * A set of all the data.
	 */
	private HashSet<T> set;
	
	/**
	 * A list of the added data.
	 */
	private LinkedList<T> list;
	
	/**
	 * Creates an empty doubly-hashed unique linked list.
	 */
	public DoublyHashedUniqueLinkedList() {
		set = new HashSet<>();
		list = new LinkedList<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addTail(T t) {
		checkNotNull(t);
		checkArgument(!set.contains(t));
		list.add(t);
		set.add(t);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean contains(T t) {
		return set.contains(t);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Set<T> getSet() {
		return set;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<T> getList() {
		return list;
	}
}
