package structural.events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import com.opencsv.exceptions.CsvException;

import structural.handlers.CsvReader;
import structural.handlers.CsvWriter;
import structural.handlers.DatabaseAccesses;
import structural.handlers.DateHandler;
import structural.handlers.PathsAccesser;
import structural.handlers.Utility;
import structural.members.Member;

/**
 * public class which handles the requesting of events by members contains 7
 * private parameters and 8 methods
 * 
 * @author gianp
 *
 */
public class RequestedEvent {
	private DatabaseAccesses databaseAccesses;
	private String eventName;
	private DateHandler date;
	private String csvFilePath;
	private Member requestingMember;
	private String requestMessage;

	/**
	 * public method which sets the name, date, member and message of the request
	 * Also sets the path to the requested events file's location saves the new
	 * request into the csv
	 * 
	 * @param eventName
	 * @param date
	 * @param requestingMember
	 * @param requestMessage
	 * @param databaseAccesses
	 */
	public RequestedEvent(String eventName, DateHandler date, Member requestingMember, String requestMessage,
			DatabaseAccesses databaseAccesses) {
		this.databaseAccesses = databaseAccesses;
		this.eventName = eventName;
		this.date = date;
		this.requestingMember = requestingMember;
		if (requestMessage == "") {
			this.requestMessage = "No message found";
		} else {
			this.requestMessage = requestMessage;
		}
		this.csvFilePath = databaseAccesses.getRequestedEventsPath();
		saveIntoCsv();
	}
	
	/**
	 * getter for the name of the event
	 * 
	 * @return
	 */
	public String getName() {
		return eventName;
	}

	/**
	 * getter for the message linked with the request
	 * 
	 * @return
	 */
	public String getRequestMessage() {
		return requestMessage;
	}

	/**
	 * private method to save requests into the csv
	 */
	private void saveIntoCsv() {
		if (!eventExistsAlready()) {
			CsvWriter csvwriter = null;
			boolean appendToFile = true;
			csvwriter = new CsvWriter(csvFilePath, appendToFile);
			String formattedDate2 = date.getRawDate();
			String[] eventInfo = { eventName, formattedDate2, requestingMember.getEmail(), requestMessage };
			csvwriter.writeEntryToFile(eventInfo);
		}
	}

	/**
	 * private method to check if an event already exists in the csv
	 * 
	 * @return
	 */
	private boolean eventExistsAlready() {
		CsvReader csvReader = new CsvReader(databaseAccesses.getRequestedEventsPath());
		List<String[]> requestedEvents = csvReader.getElementsFromCsv();
		for (int i_event = 0; i_event < requestedEvents.size(); i_event++) {
			String[] reference = getInformationAsArray();
			String[] Input = requestedEvents.get(i_event);
			boolean eventsAreSame = Arrays.equals(Input, reference);
			if (eventsAreSame) {
				return true;
			}
		}
		return false;
	}

	/**
	 * public method to accept requests from the requested events csv creates a new
	 * event object and saves it to the event database
	 */
	public void acceptRequest() {
		Event event = new Event(this.eventName, this.date, new PathsAccesser());
		event.saveEventToCsv();
		String dateInfo = date.getRawDate();
		String[] rowToDelete = { this.eventName, dateInfo };
		Utility.deleteElementFromCsv(csvFilePath, rowToDelete);
	}

	/**
	 * public method to decline a request Deletes the request from the requested
	 * events database
	 */
	public void declineRequest() {
		Utility.deleteElementFromCsv(csvFilePath, getInformationAsArray());
	}

	/**
	 * public method to obtain information of request and member sending request in
	 * array returns an array containing event name, event date, member email and
	 * request message.
	 * 
	 * @return
	 */
	public String[] getInformationAsArray() {
		String formattedDate = date.getRawDate();
		String[] eventInfo = { eventName, formattedDate, requestingMember.getEmail(), requestMessage };

		return eventInfo;
	}

}
