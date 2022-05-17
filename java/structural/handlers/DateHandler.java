package structural.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * public class which streamlines all forms of date usage into one common format
 * outputs a date formatted to be "dd-mm-yyyy" contains 5 private variables and
 * 16 methods
 * 
 * @author gianp
 *
 */
public class DateHandler {
	private String rawDate;
	private Date date;

	private LocalDate localDate;
	private String datePattern = "dd-MM-yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	/**
	 * public methods which takes a date in string format and formats it to a date
	 * object. if a string date is given in wrong format, an error is shown
	 * 
	 * @param rawDate
	 */
	public DateHandler(String rawDate) {
		this.rawDate = rawDate;
		try {
			date = dateFormatter.parse(rawDate);
		} catch (ParseException e) {
			System.out.println("date must have the right format: dd-MM-yyyy");
			e.printStackTrace();
		}
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * public method which takes a localDate date and transforms it into a string
	 * rawdate if a localDate with wrong format is given, an error is shown
	 * 
	 * @param localDate
	 */
	public DateHandler(LocalDate localDate) {
		this.localDate = localDate;
		int day = localDate.getDayOfMonth();
		int month = localDate.getMonthValue();
		int year = localDate.getYear();
		String dayClean = dayMonthToString(day);
		String monthClean = dayMonthToString(month);

		this.rawDate = dayClean + "-" + monthClean + "-" + year;
		try {
			date = dateFormatter.parse(rawDate);
		} catch (ParseException e) {
			System.out.println("date must have the right format: dd-MM-yyyy");
			e.printStackTrace();
		}

	}

	/**
	 * getter for raw date
	 * 
	 * @return
	 */
	public String getRawDate() {
		return rawDate;
	}

	/**
	 * setter for raw date
	 * 
	 * @param rawDate
	 */
	public void setRawDate(String rawDate) {
		this.rawDate = rawDate;
	}

	/**
	 * getter for Date
	 * 
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * setter for Date
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * getter for LocalDate
	 * 
	 * @return
	 */
	public LocalDate getLocalDate() {
		return localDate;
	}

	/**
	 * setter for LocalDate
	 * 
	 * @param localDate
	 */
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	/**
	 * getter for DatePattern
	 * 
	 * @return
	 */
	public String getDatePattern() {
		return datePattern;
	}

	/**
	 * setter for DatePattern
	 * 
	 * @param datePattern
	 */
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	/**
	 * getter for date formatter
	 * 
	 * @return
	 */
	public SimpleDateFormat getDateFormatter() {
		return dateFormatter;
	}

	/**
	 * setter for DateFormatter
	 * 
	 * @param dateFormatter
	 */
	public void setDateFormatter(SimpleDateFormat dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	/**
	 * private method which takes an integer value, if int value is above 9, a 0 is
	 * added before the 9 for visualization purposses if in value is 10 or above,
	 * the integer is converted to a string
	 * 
	 * @param value
	 * @return
	 */
	private String dayMonthToString(int value) {
		if (value < 10) {
			String valueClean = "0" + value;
			return valueClean;
		} else {
			String valueClean = "" + value;
			return valueClean;
		}
	}

	/**
	 * static public method converts Date date object to localDate object
	 * 
	 * @param date
	 * @return
	 */
	static public LocalDate convertDateToLocalDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}
	/**
	 * static public getter method which extracts dates from current date to one week ahead.
	 * returns all dates in one week from given start point
	 * @return
	 */
	static public DateHandler getDateInOneWeek() {
		LocalDate currentDate = LocalDate.now();
		DateHandler dateInOneWeek = new DateHandler(currentDate.plus(1, ChronoUnit.WEEKS));

		return dateInOneWeek;
	}
	/**
	 * static public getter for the current date
	 * returns the current date.
	 * @return
	 */
	static public DateHandler getCurrentDate() {
		LocalDate now = LocalDate.now();
		DateHandler currentDate = new DateHandler(now);

		return currentDate;
	}
}