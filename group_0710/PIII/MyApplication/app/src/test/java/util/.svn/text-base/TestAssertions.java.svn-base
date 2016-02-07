package util;

import static org.junit.Assert.*;

import org.junit.Test;

import static util.Assertions.*;

/**
 * Tests the assertions class.
 * 
 * @author Chris
 */
public class TestAssertions {

	/**
	 * Tests that an object which is valid doesn't throw an exception.
	 */
	@Test
	public void testNotNull() {
		checkNotNull(new Object());
	}
	
	/**
	 * Tests that a null reference causes an exception.
	 */
	@Test(expected = NullPointerException.class)
	public void testNull() {
		checkNotNull(null);
	}
	
	/**
	 * Tests a non-null object with a valid test message.
	 */
	@Test
	public void testNotNullMessage() {
		checkNotNull(new Object(), "test");
	}
	
	/**
	 * Tests a non-null object with a null test message.
	 */
	@Test
	public void testNotNullMessageNull() {
		checkNotNull(new Object(), null);
	}
	
	/**
	 * Tests a null check with a valid message.
	 */
	@Test
	public void testNullMessage() {
		try {
			checkNotNull(null, "Message");
			fail();
		} catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("Message"));
		}
	}
	
	/**
	 * Tests a null reference with a null message.
	 */
	@Test
	public void testNullMessageNull() {
		try {
			checkNotNull(null, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), null);
		}
	}
	
	/**
	 * Tests that a valid argument throws no exception.
	 */
	@Test
	public void testValidArg() {
		checkArgument(true);
	}
	
	/**
	 * Tests that an invalid expression throws an argument.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArg() {
		checkArgument(false);
	}
	
	/**
	 * Tests that a valid argument throws no exception with a message.
	 */
	@Test
	public void testValidArgMessage() {
		checkArgument(true, "test");
	}
	
	/**
	 * Tests that an invalid expression throws an argument with a message.
	 */
	@Test
	public void testInvalidArgMessage() {
		try {
			checkArgument(false, "reason");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("reason"));
		}
	}
	
	/**
	 * Tests that an invalid expression throws an argument with a null message.
	 */
	@Test
	public void testInvalidArgMessageNull() {
		try {
			checkArgument(false, null);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage() == null);
		}
	}
}
