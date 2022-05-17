package structural.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
/**
 * public class which inherits from abstract class abstractFormatter, turns strings to values and vice versa
 * contains 2 private parameters and 2 methods
 * @author gianp
 *
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

	private String datePattern = "dd-MM-yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	/**
	 * public method which initiates a calendar,
	 * converts a string to date and sets the converted date to the calendar
	 * returns a calendar object
	 * 		
	 */
	public Object stringToValue(String dateRaw) {
		
		Calendar date = Calendar.getInstance();
        try {
        	date.setTime((Date) dateFormatter.parseObject(dateRaw));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
	}
	/**
	 * public method which overrides a method from the abstract class to turn a value to string
	 * it returns null if the value is 0
	 * it returns a date formatted string from the calendar object
	 */
	@Override
	public String valueToString(Object date)  {
		if (date != null) {
			Calendar dateCalendar = (Calendar) date;
			return dateFormatter.format(dateCalendar.getTime());
		}
		return null;
	}

}