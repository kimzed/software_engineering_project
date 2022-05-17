package junit_test;

import junit.framework.TestCase;
import structural.handlers.DateLabelFormatter;
/**
 * Junit test class which handles the tests regarding the dateformatter class
 * contains 1 test
 * @author gianp
 *
 */
public class DateFormatterTest extends TestCase {
	/**
	 * tests if the conversions of string to value and vice versa is done correctly
	 */
	public void testStringToValueAndValueToString_StringFormattingStaysSame() {
		String rawDate = "30-11-2001";
		DateLabelFormatter dateFormatter = new DateLabelFormatter();
		Object date = dateFormatter.stringToValue(rawDate);
		String formattedDate = dateFormatter.valueToString(date);
		assertEquals(formattedDate,rawDate);
		
	}
}
