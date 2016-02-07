package util;

import static org.junit.Assert.*;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.Test;

/**
 * Tests the constants class methods.
 * 
 * @author Chris
 */
public class TestConstants {

	/**
	 * Tests the class can be parsed properly.
	 */
	@Test
	public void testDateConverter() {
		LocalDate date = Constants.parseDate("2014-03-12");
		assertEquals(2014, date.getYear());
		assertEquals(3, date.getMonthValue());
		assertEquals(12, date.getDayOfMonth());
	}
	
	/**
	 * Tests invalid date data.
	 */
	@Test(expected = DateTimeException.class)
	public void testInvalidDate() {
		Constants.parseDate("2014-15-12");
	}
	
	/**
	 * Tests that there is actual numbers.
	 */
	@Test(expected = NumberFormatException.class)
	public void testNumberCorruptionDate() {
		Constants.parseDate("2014-ab-12");
	}
	
	/**
	 * Tests for invalid parts.
	 */
	@Test(expected = DateTimeException.class)
	public void testCorruptDate() {
		Constants.parseDate("2014-154-12");
	}
	
	/**
	 * Test for nulls.
	 */
	@Test(expected = NullPointerException.class)
	public void testNull() {
		Constants.parseDate(null);
	}
}
