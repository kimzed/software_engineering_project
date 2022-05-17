package structural.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.exceptions.CsvException;

import structural.handlers.CsvReader;
import structural.handlers.CsvWriter;
import structural.handlers.DatabaseAccesses;
import structural.handlers.DateHandler;
import structural.handlers.PathsAccesser;
import structural.handlers.StringInformationObject;
import structural.handlers.Utility;
import structural.members.Member;

/**
 * Public class that creates events with a certain name, date and possibility to
 * add participants.
 * contains 8 private parameters and 23 methods
 */
public class Event implements Comparable<Event>, StringInformationObject {

	private String name;
	private DateHandler date;
	private File participantsFile;
	private List<String> participantNames;
	private String participantsListPath;
	private String eventScheduleFile;
	private List<String> participantEmails;
	private DatabaseAccesses databaseAccesses;

	/**
	 * Constructor of class Event which requires name, date and access to file paths
	 * for storage.
	 * 
	 * A parameter of the class DatabaseAcesses is needed, which can return the
	 * filename where the event is stored as well as the path where the file, which
	 * contains the participants of the event, is created.
	 * 
	 * @param name
	 * @param date
	 * @param databaseAccesses
	 */
	public Event(String name, DateHandler date, DatabaseAccesses databaseAccesses) {
		this.name = name;
		this.date = date;
		this.databaseAccesses = databaseAccesses;
		this.eventScheduleFile = databaseAccesses.getEventSchedulePath();
		String dateFormatted = date.getRawDate();
		String participantsFileName = dateFormatted + name + ".csv";
		this.participantsListPath = databaseAccesses.getEventParticipantsFolderPath() + participantsFileName;
		createCsvFile();
	}

	/**
	 * Public method that gets the file path of the file where the event is stored.
	 * 
	 * @return
	 */
	public String getEventScheduleFile() {
		return eventScheduleFile;
	}

	/**
	 * Public method that
	 * 
	 * @param eventScheduleFile
	 */
	public void setEventScheduleFile(String eventScheduleFile) {
		this.eventScheduleFile = eventScheduleFile;
	}

	/**
	 * Public method that returns the name of the event.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Public method that gives the number of participants that is registered for
	 * the event. Return type is integer.
	 * 
	 * @return
	 */
	public int getNumberOfParticipants() {
		readParticipants();
		return participantNames.size();
	}

	/**
	 * Public method that returns the date of the event.
	 */
	public LocalDate getDate() {
		return date.getLocalDate();
	}

	/**
	 * Public method that allows to change the name of an event.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Public method that allows to change the date of the event. Requires a date of
	 * type DateHandler.
	 * 
	 * @param date
	 */
	public void setDate(DateHandler date) {
		this.date = date;
	}

	/**
	 * Public method that returns the file location of the participants list
	 */
	public String getParticipantsListPath() {
		return participantsListPath;
	}
	/**
	 * private getter for the names of participants
	 * @return
	 */
	private List<String> getParticipantNames() {
		readParticipants();
		return participantNames;
	}

	/**
	 * Public method that returns a list of the names of the participants of an
	 * event.
	 * 
	 * @throws IOException
	 */
	public List<String> getParticipantEmails() {

		readParticipantsEmail();
		return participantEmails;
	}

	/**
	 * Public method that saves the name and the date of an event to the event
	 * schedule.
	 * 
	 * File location of the event schedule can be changed in method
	 * {@code setEventScheduleFile}.
	 */
	public void saveEventToCsv() {
		if (eventExistsAlready() == false) {
			boolean appendToFile = true;
			CsvWriter csvwriter = new CsvWriter(getEventScheduleFile(), appendToFile);
			csvwriter.writeEntryToFile(getInformationAsArray());
		}
	}
	/**
	 * private method to create a new participant csv file
	 * gives error is the path is not correct
	 */
	private void createCsvFile() {
		try {
			this.participantsFile = new File(participantsListPath);
			participantsFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Path is not correct: " + this.participantsFile);
		}
	}
	/**
	 * public method which overrides stringinformationObject function compareTo
	 */
	@Override
	public int compareTo(Event otherEvent) {
		LocalDate dateEvent = this.date.getLocalDate();
		LocalDate otherEventDate = otherEvent.date.getLocalDate();
		return dateEvent.compareTo(otherEventDate);
	}

	/**
	 * Public class that adds a {@link Member} to the CSV-file where the event
	 * participants are stored.
	 * 
	 * @param member
	 */
	public void addParticipant(Member member) {
		String name = member.getName();
		String dateOfBirth = member.getDateOfBirth();
		String email = member.getEmail();
		String[] participantInformation = { name, dateOfBirth, email };
		if (participantExistsAlready(member) == false) {
			Utility.addRowToCsv(participantInformation, participantsListPath);
		}
	}

	/**
	 * Public class that returns the name and date of event as array.
	 *
	 * returns the following array: {name, date}
	 */
	public String[] getInformationAsArray() {
		String formattedDate = date.getRawDate();
		String[] eventInformation = { name, formattedDate };

		return eventInformation;
	}
	/**
	 * private method which checks if the event already exists in the eventSchedule csv
	 * @return
	 */
	private boolean eventExistsAlready() {
		EventSchedule eventSchedule = new EventSchedule(new PathsAccesser());
		List<Event> events = eventSchedule.getEvents();
		return Utility.elementAlreadyExists(this, events);
	}

	/**
	 * Public method that reads the names of the participants from the CSV into a
	 * list.
	 */
	public void readParticipants() {

		CsvReader csvReader = new CsvReader(participantsListPath);
		List<String[]> membersRows = csvReader.getElementsFromCsv();
		participantNames = new ArrayList<String>();
		for (int i_participant = 0; i_participant < membersRows.size(); i_participant++) {
			String[] participantInfo = membersRows.get(i_participant);
			String name = (participantInfo[0]);
			participantNames.add(name);
		}
	}

	/**
	 * Public method that reads the email addresses of the participants from the CSV
	 * into a list.
	 */
	public void readParticipantsEmail() {

		CsvReader csvReader = new CsvReader(this.participantsListPath);
		List<String[]> membersRows = csvReader.getElementsFromCsv();
		participantEmails = new ArrayList<String>();
		for (int i_participant = 0; i_participant < membersRows.size(); i_participant++) {
			String[] participantInfo = membersRows.get(i_participant);
			String email = (participantInfo[2]);
			participantEmails.add(email);
		}
	}

	/**
	 * Public method that removes the event from the CSV file where the events is
	 * scheduled
	 */
	public void removeFromSchedule() {
		String formattedDate = date.getRawDate();
		String[] infoRow = { name, formattedDate };
		Utility.deleteElementFromCsv(eventScheduleFile, infoRow);

	}

	/**
	 * Public method that removes a participant ({@link Member}) from the CSV file
	 * with the participants.
	 */
	public void removeParticipant(Member member) {
		String name = member.getName();
		String dateOfBirth = member.getDateOfBirth();
		String email = member.getEmail();
		String[] participantInformation = { name, dateOfBirth, email };
		Utility.deleteElementFromCsv(participantsListPath, participantInformation);

	}

	/**
	 * Public method that checks if a {@link Member} is already registered for an
	 * event. Returns true if this is the case.
	 */
	public boolean participantExistsAlready(Member member) {
		String[] infoParticipant = { member.getName(), member.getDateOfBirth(), member.getEmail() };
		return Utility.rowIsInCsv(infoParticipant, participantsListPath);
	}
	/**
	 * public method which extract events which are scheduled to happen within a certain period
	 * returns extracted events
	 */
	public boolean isWithinPeriod(DateHandler dateBegin, DateHandler dateEnd) {
		LocalDate dateEvent = getDate();

		boolean eventWithinTwoDates = dateEvent.isAfter(dateBegin.getLocalDate())
				& dateEvent.isBefore(dateEnd.getLocalDate());

		boolean eventWithinPeriod = dateEvent.isEqual(dateBegin.getLocalDate())
				| dateEvent.isEqual(dateEnd.getLocalDate()) | eventWithinTwoDates;

		return eventWithinPeriod;
	}
}
