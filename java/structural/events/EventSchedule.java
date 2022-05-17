package structural.events;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import structural.handlers.CsvReader;
import structural.handlers.DatabaseAccesses;
import structural.handlers.DateHandler;
import structural.handlers.Utility;

import java.util.Collections;

/**
 * public class which handles reading, viewing and sorting of the event schedule
 * contains 3 private variables and 9 methods
 * 
 * @author gianp
 *
 */
public class EventSchedule {

	private String filePath;
	private List<Event> events;
	private DatabaseAccesses databaseAccesses;

	/**
	 * public method to set the path to the event schedule location, then reads and
	 * sorts the events from the csv
	 * 
	 * @param databaseAccesses
	 */
	public EventSchedule(DatabaseAccesses databaseAccesses) {
		this.databaseAccesses = databaseAccesses;
		this.filePath = databaseAccesses.getEventSchedulePath();

		readEvents();
		sortByDate();
	}
	
	/**
	 * getter for events
	 * 
	 * @return
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * setter for events
	 * 
	 * @return
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	/**
	 * private method which opens the csv file and extracts the elements, if the
	 * date has passed, the event is removed from the csv
	 */
	private void readEvents() {
		events = new ArrayList<Event>();
		CsvReader csvReader = new CsvReader(filePath);
		List<String[]> eventsRows = csvReader.getElementsFromCsv();

		for (int i_event = 0; i_event < eventsRows.size(); i_event++) {
			String[] row = eventsRows.get(i_event);
			String name = (row[0]);
			String rawDate = (row[1]);
			DateHandler date = new DateHandler(rawDate);

			boolean eventIsPast = date.getLocalDate().isBefore(LocalDate.now());

			if (eventIsPast) {
				Utility.deleteElementFromCsv(filePath, row);
			} else {
				Event event = new Event(name, date, databaseAccesses);
				events.add(event);
			}

		}
	}

	/**
	 * public method which checks if an event already exists in the database returns
	 * a boolean value indicating the if the event already exists
	 * 
	 * @param event
	 * @return
	 */
	public boolean eventAlreadyExists(Event event) {

		return Utility.elementAlreadyExists(event, events);
	}

	/**
	 * public method which views the current event schedule returns a object
	 * "output" containing all currently scheduled event names and dates
	 * 
	 * @return
	 */
	public Object[][] viewSchedule() {

		int numberOfEvents = events.size();
		Object[][] output = new Object[numberOfEvents][2];

		for (int i_event = 0; i_event < numberOfEvents; i_event++) {
			Event event = events.get(i_event);
			String[] eventInfo = event.getInformationAsArray();
			Object name = (eventInfo[0]);
			String date = (eventInfo[1]);
			output[i_event][0] = name;
			output[i_event][1] = date;
		}
		return output;
	}

	

	/**
	 * private method which sorts the events in the csv by date
	 */
	private void sortByDate() {
		Collections.sort(events);
	}
	/**
	 * public method which extracts events which are scheduled this week
	 * returns arraylist containing events scheduled this week
	 * @return
	 */
	public ArrayList<Event> getEventsThisWeek() {
		DateHandler currentDate = DateHandler.getCurrentDate();
		DateHandler dateInOneWeek = DateHandler.getDateInOneWeek();
		ArrayList<Event> eventsOfTheWeek = GetEventsWithinPeriod(currentDate, dateInOneWeek);

		return eventsOfTheWeek;
	}
	/**
	 * public method which extract events which are scheduled in a certain period of time
	 * returns arraylist containing events scheduled within the given time period
	 * @param dateBegin
	 * @param dateEnd
	 * @return
	 */
	public ArrayList<Event> GetEventsWithinPeriod(DateHandler dateBegin, DateHandler dateEnd) {
		ArrayList<Event> selectedEvents = new ArrayList<Event>();

		for (Event event : events) {
			LocalDate dateEvent = event.getDate();
			boolean eventWithinPeriod = event.isWithinPeriod(dateBegin, dateEnd);

			if (eventWithinPeriod) {
				selectedEvents.add(event);
			}
		}

		return selectedEvents;
	}
}
