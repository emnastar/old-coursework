package util;

import static org.junit.Assert.*;

import org.junit.Test;

import util.HashedLinkedList;

/**
 * Tests the doubly hashed unique linked list.
 * 
 * @author Chris
 */
public class TestDoublyHashedUniqueLinkedList {

	/**
	 * Makes sure the constructor creates a blank object.
	 */
	@Test
	public void testConstructor() {
		HashedLinkedList<?> dl = new DoublyHashedUniqueLinkedList<Object>();
		assertTrue(dl.size() == 0);
		assertTrue(dl.getSet().size() == 0);
		assertTrue(dl.getList().size() == 0);
	}
	
	/**
	 * Tests tail addition of elements. This also tests contains/size/set/list.
	 */
	@Test
	public void testTail() {
		HashedLinkedList<String> dl = new DoublyHashedUniqueLinkedList<String>();
		
		dl.addTail("Hi");
		assertTrue(dl.size() == 1);
		assertTrue(dl.getSet().size() == 1);
		assertTrue(dl.getList().size() == 1);
		assertTrue(dl.getSet().contains("Hi"));
		assertTrue(dl.getList().isEmpty() == false);
		assertTrue(dl.getList().get(0).equals("Hi"));
		
		dl.addTail("Hello");
		assertTrue(dl.size() == 2);
		assertTrue(dl.getSet().size() == 2);
		assertTrue(dl.getList().size() == 2);
		assertTrue(dl.getSet().contains("Hi"));
		assertTrue(dl.getSet().contains("Hello"));
		assertTrue(dl.getList().get(0).equals("Hi"));
		assertTrue(dl.getList().get(1).equals("Hello"));
	}
	
	/**
	 * Tests that contains works.
	 */
	@Test
	public void testContains() {
		HashedLinkedList<String> dl = new DoublyHashedUniqueLinkedList<String>();
		
		dl.addTail("Hi");
		dl.addTail("Hello");
		dl.addTail("Yes");
		
		assertTrue(dl.contains("Hi"));
		assertTrue(dl.contains("Yes"));
		assertTrue(dl.contains("Hello"));
		
		assertFalse(dl.contains(""));
	}
	
	/**
	 * Tests that checking for null works.
	 */
	@Test
	public void testContainsNull() {
		assertFalse(new DoublyHashedUniqueLinkedList<String>().contains(null));
	}
	
	/**
	 * Tests to make sure nulls can't sneak into the data.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullAddition() {
		new DoublyHashedUniqueLinkedList<String>().addTail(null);
	}
	
	/**
	 * Tests that the same element can't be added.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSameElementAddition() {
		HashedLinkedList<String> dl = new DoublyHashedUniqueLinkedList<String>();
		dl.addTail("a");
		dl.addTail("Hi");
		dl.addTail("Hello");
		dl.addTail("Yes");
		dl.addTail("Hi");
	}
}
