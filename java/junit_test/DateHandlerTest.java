package junit_test;

import java.time.LocalDate;

import junit.framework.TestCase;
import structural.handlers.DateHandler;
/**
 * Junit test class which handles the tests regarding the dateHandler class
 * contains 1 test
 * @author gianp
 *
 */
public class DateHandlerTest extends TestCase {
	/**
	 * tests if the local date has the right formatting(DD-MM_YYYY)
	 */
	public void testConstructor_LocalDateHasRightFormatting() {
		String expectedString = "01-12-2000";
		LocalDate localDate = LocalDate.of(2000, 12, 01);

		DateHandler dateHandler = new DateHandler(localDate);
		String dateFormatted = dateHandler.getRawDate();

		assertTrue(dateFormatted.equals(expectedString));

	}
}
