package parser;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Tests the CsvParser class.
 * 
 * @author David
 */
public class TestCsvParser extends TestCase {

	/**
	 * Makes sure the array isn't empty.
	 * 
	 * @throws Exception
	 *             If any exceptions occur.
	 */
	@Test
	public void testArrayListIsNotEmpty() throws Exception {
		setUp();
		CsvParser fileList = new CsvParser("res/test.txt");
		assertFalse("should return false", fileList.getInfo().isEmpty());
		tearDown();
	}

	/**
	 * Makes sure the size is valid.
	 * 
	 * @throws Exception
	 *             If any exceptions occur.
	 */
	@Test
	public void testArrayListSize() throws Exception {
		setUp();
		CsvParser fileList = new CsvParser("res/test.txt");
		assertEquals("size of list is five", 5, fileList.getInfo().size());
		tearDown();
	}

	@Test
	public void testArrayListCheckElement1() throws Exception {
		setUp();
		CsvParser fileList = new CsvParser("res/test.txt");
		assertEquals("checks the first element of first list", "1",
				fileList.getInfo().get(0)[0]);
		tearDown();
	}

	/**
	 * Makes sure the second element is valid.
	 * 
	 * @throws Exception
	 *             If any exceptions occur.
	 */
	@Test
	public void testArrayListCheckElement2() throws Exception {
		setUp();
		CsvParser fileList = new CsvParser("res/test.txt");
		assertEquals("checks the second element of third list",
				"2015-05-11 04:30", fileList.getInfo().get(2)[1]);
		tearDown();
	}

	/**
	 * Tests that any file loading errors are valid.
	 * 
	 * @throws IOException
	 *             If any exceptions occur.
	 */
	public void testFileNotFound() throws IOException {
		try {
			new CsvParser("CANT_BE_FOUND");
			fail();
		} catch (FileNotFoundException e) {
		}
	}
}
