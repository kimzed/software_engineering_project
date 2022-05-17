package junit_test;

import junit.framework.TestCase;
import structural.events.Event;
import structural.handlers.CsvReader;
import structural.handlers.DateHandler;
import structural.handlers.Utility;
import structural.members.Member;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
 /**
 * Junit test class which handles the tests regarding the event class
 * contains 2 private parameters and 10 tests
 * @author gianp
 *
 */
public class EventTest extends TestCase {

	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	private String eventParticipantsFolder = pathAccesser.getEventParticipantsFolderPath();

	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests if the name a newly created event is saved correctly to the event list
	 */
	public void testConstructor_NameIsCorrect() {

		String nameEvent = "course1";

		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2020"), new FakePathsAccesser());

		assertEquals(eventCourse.getName(), nameEvent);
	}
	/**
	 * tests if the date of a newly created event is saved correctly to the event list
	 */
	public void testConstructor_DateIsCorrect() {

		String nameEvent = "course2";

		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2021"), new FakePathsAccesser());

		assertEquals(eventCourse.getDate(), new DateHandler("01-01-2021").getLocalDate());

	}
	/**
	 * tests if the file path is set correctly in the constructor
	 */
	public void testConstructor_FilePathIsCorrect() {

		String nameEvent = "course90";
		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2024"), new FakePathsAccesser());
		String path1 = eventParticipantsFolder + "01-01-2024course90.csv";
		assertTrue(path1.equals(eventCourse.getParticipantsListPath()));
	}
	/**
	 * tests if the file is correctly created on the designated path
	 */
	public void testConstructor_FileIsCorrectlyCreated() {

		String nameEvent = "course80";
		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());

		String filename = eventCourse.getParticipantsListPath();
		CsvReader csvReader = new CsvReader(filename);
		String[] lines = csvReader.getLinesFromCsv();
		int numberlines = lines.length;
		assertEquals(numberlines, 0);
	}
	/**
	 * tests if participants are added correctly to the event's participation csv
	 */
	public void testAddParticipant_ParticipantAddedToCsv() {
		Member testMember1 = new Member("Jan Lid", "12/23/2334", "qwerty", "email");
		Member testMember2 = new Member("Piet Lid", "12/23/2334", "qwerty", "email");
		String nameEvent = "course4";

		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());
		String filename = eventCourse.getParticipantsListPath();
		int numberlinesBeforeParticipantIsAdded = Utility.getNumberOfRowsCsv(filename);
		eventCourse.addParticipant(testMember2);
		int numberlinesAfterParticipantIsAdded = Utility.getNumberOfRowsCsv(filename);

		assertEquals(numberlinesAfterParticipantIsAdded, numberlinesBeforeParticipantIsAdded + 1);

	}
	/**
	 * tests if a single participant is added correclty to the event's participation csv
	 */
	public void testGetNumberOfParticipants_AddsOneParticipant() {
		Member testMember = new Member("Jan Lid", "12/23/2334", "qwerty", "email");
		String nameEvent = "course5";

		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());
		eventCourse.addParticipant(testMember);
		String filename = eventCourse.getParticipantsListPath();

		eventCourse.readParticipants();
		int numberOfParticipants = eventCourse.getNumberOfParticipants();
		assertTrue(numberOfParticipants != 0);
	}
	/**
	 * tests if a newly created event is correctly added to the eventschedule csv
	 */
	public void testSaveEventToCsv_IsAddedToScheduleFile() {
		CsvReader csvReaderStart = new CsvReader("data/unit_test/EventSchedule/EventSchedule.csv");
		String[] linesStart = csvReaderStart.getLinesFromCsv();
		int numberlinesStart = linesStart.length;

		String nameEvent = "course6";
		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());
		eventCourse.setEventScheduleFile("data/unit_test/EventSchedule/EventSchedule.csv");
		eventCourse.saveEventToCsv();

		CsvReader csvReaderFinal = new CsvReader("data/unit_test/EventSchedule/EventSchedule.csv");
		String[] linesFinal = csvReaderFinal.getLinesFromCsv();
		int numberlinesFinal = linesFinal.length;
		eventCourse.removeFromSchedule();
		assertEquals(numberlinesStart + 1, numberlinesFinal);

	}
	/**
	 * tests if the creation of a new csv file does not overwrite a already existing csv with the same name
	 */
	public void testConstructor_FileCreationDoesntOverwriteFile() {

		String nameEvent = "course6";

		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());

		Member testMember = new Member("Jan jansen", "12/23/2020", "qwerty", "email");

		eventCourse.addParticipant(testMember);

		eventCourse = null;

		Event eventCourseAfter = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());
		eventCourseAfter.readParticipants();

		boolean participantIsInCsv = eventCourseAfter.participantExistsAlready(testMember);

		assertTrue(participantIsInCsv);

	}
	/**
	 * tests if the removal of a participant from a event's participation csv is done correctly
	 */
	public void testRemoveParticipant_ParticipantIsRemovedFromDatabase() {
		String nameEvent = "course6";

		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());
		String fileEvent = eventCourse.getParticipantsListPath();

		Member testMember = new Member("William jansen", "12/23/2020", "qwerty", "email");
		eventCourse.addParticipant(testMember);
		int numberOfLinesBefore = Utility.getNumberOfRowsCsv(fileEvent);
		eventCourse.removeParticipant(testMember);
		int numberOfLinesAfter = Utility.getNumberOfRowsCsv(fileEvent);
		assertEquals(numberOfLinesBefore - 1, numberOfLinesAfter);

	}
	/**
	 * tests if a participant cannot be added twice to an event's participation csv
	 */
	public void testAddParticipant_ParticipantCantBeAddedTwice() {
		String nameEvent = "course7";

		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2022"), new FakePathsAccesser());
		String fileEvent = eventCourse.getParticipantsListPath();
		Member testMember = new Member("Grapjas", "12-19-1919", "asdf", "email");
		eventCourse.addParticipant(testMember);
		eventCourse.addParticipant(testMember);

		CsvReader csvReader = new CsvReader(fileEvent);
		String[] lines = csvReader.getLinesFromCsv();
		int numberlines = lines.length;
		assertEquals(numberlines, 1);
	}

	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}
}
