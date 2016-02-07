package util;

/**
 * A collection of assertions that are used to create readable code and error
 * out when certain preconditions are not met. This is inspired from Google's
 * Guava library.
 * 
 * @author Chris
 */
public class Assertions {

	/**
	 * Checks if the object is null or not.
	 * 
	 * @param o
	 * 		The object to check.
	 * 
	 * @throws NullPointerException
	 * 		If the object is null.
	 */
	public static void checkNotNull(Object o) {
		checkNotNull(o, null);
	}
	
	/**
	 * Checks if the object is null or not, with a provided error message.
	 * 
	 * @param o
	 * 		The object to check.
	 * 
	 * @param message
	 * 		The message for the exception if the object is null.
	 * 
	 * @throws NullPointerException
	 * 		If the object is null.
	 */
	public static void checkNotNull(Object o, String message) {
		if (o == null) {
			throw new NullPointerException(message);
		}
	}
	
	/**
	 * Checks that the condition of the argument holds. 
	 * 
	 * @param condition
	 * 		The condition to check (errors out if false).
	 * 
	 * @throws IllegalArgumentException
	 * 		If the argument is false.
	 */
	public static void checkArgument(boolean condition) {
		checkArgument(condition, null);
	}
	
	/**
	 * Checks that the condition of the argument holds, and has a reason for
	 * any exceptions.
	 * 
	 * @param condition
	 * 		The condition to check (errors out if false).
	 * 
	 * @param message
	 * 		The error message if an exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 * 		If the argument is false.
	 */
	public static void checkArgument(boolean condition, String message) {
		if (!condition) {
			throw new IllegalArgumentException(message);
		}
	}
}
