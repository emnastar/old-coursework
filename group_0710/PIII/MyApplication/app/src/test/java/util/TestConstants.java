package util;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;

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
		assertEquals(3, date.getMonthOfYear());
		assertEquals(12, date.getDayOfMonth());
	}
	
	/**
	 * Tests that there is actual numbers.
	 */
	@Test(expected = NumberFormatException.class)
	public void testNumberCorruptionDate() {
		Constants.parseDate("2014-ab-12");
	}

	/**
	 * Test for nulls.
	 */
	@Test(expected = NullPointerException.class)
	public void testNull() {
		Constants.parseDate(null);
	}
}
