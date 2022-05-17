package junit_test;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import structural.events.Event;
import structural.events.EventSchedule;
import structural.handlers.CsvReader;
import structural.handlers.DateHandler;
import structural.handlers.PathsAccesser;
import structural.handlers.Utility;
import structural.members.Member;
import structural.members.MemberList;
/**
 * Junit test class which handles the tests regarding the event schedule class
 * contains 3 parameters and 9 tests
 * @author gianp
 *
 */
public class EventScheduleTest extends TestCase {
	
	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	private String eventParticipantsFolder = pathAccesser.getMembersPath();
	private String eventSchedulePath = pathAccesser.getEventSchedulePath();

	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests if the number of events is read correctly from the csv
	 */
	public void testReadevents_NumberOfEventsIsCorrect() {

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);

		CsvReader csvReader = new CsvReader(eventSchedulePath);
		String[] lines = null;
		lines = csvReader.getLinesFromCsv();
		int numberlines = lines.length;
		List<Event> events = eventSchedule.getEvents();
		assertEquals(numberlines, events.size());

	}
	/**
	 * tests if a past event is deleted from the csv
	 */
	public void testReadEvents_PastEventIsDeletedFromCsv() {

		String nameEvent = "past_course";
		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2000"), pathAccesser);
		eventCourse.setEventScheduleFile(eventSchedulePath);
		eventCourse.saveEventToCsv();

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);

		String[] informationEvent = eventCourse.getInformationAsArray();
		boolean eventInCsv = Utility.rowIsInCsv(informationEvent, eventSchedulePath);

		assertFalse(eventInCsv);

	}
	/**
	 * tests if a past event is deleted from the event list
	 */
	public void testReadEvents_PastEventIsDeletedFromList() {

		String nameEvent = "past_course";
		Event eventCourse = new Event(nameEvent, new DateHandler("01-01-2000"), pathAccesser);
		eventCourse.setEventScheduleFile(eventSchedulePath);
		eventCourse.saveEventToCsv();

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);

		List<Event> events = eventSchedule.getEvents();
		boolean eventIsInList = eventSchedule.eventAlreadyExists(eventCourse);

		assertFalse(eventIsInList);

	}
	/**
	 * tests if the events are ordered correctly by date when using getEvents
	 */
	public void testSortByDate_OrderIsCorrect() {
		Event eventLate = new Event("Late Event", new DateHandler("01-01-2500"), pathAccesser);
		eventLate.setEventScheduleFile(eventSchedulePath);
		eventLate.saveEventToCsv();

		Event eventEarly = new Event("Early Event", new DateHandler("01-01-2400"), pathAccesser);
		eventEarly.setEventScheduleFile(eventSchedulePath);
		eventEarly.saveEventToCsv();

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);
		List<Event> events = eventSchedule.getEvents();
		int numberOfEvents = events.size();
		Event lastEvent = events.get(numberOfEvents - 1);
		String lastEventName = lastEvent.getName();
		String eventLateName = eventLate.getName();

		Utility.deleteElementFromCsv(eventSchedulePath, eventEarly.getInformationAsArray());
		Utility.deleteElementFromCsv(eventSchedulePath, eventLate.getInformationAsArray());

		assertTrue(eventLateName.equals(lastEventName));

	}
	/**
	 * tests if the events are sorted correctly by date when using viewSchedule
	 */
	public void testViewSchedule_EventsAreSortedByDate() {
		Event eventLate = new Event("Late Event", new DateHandler("01-01-2500"), pathAccesser);
		eventLate.setEventScheduleFile(eventSchedulePath);
		eventLate.saveEventToCsv();

		Event eventEarly = new Event("Early Event", new DateHandler("01-01-2400"), pathAccesser);
		eventEarly.setEventScheduleFile(eventSchedulePath);
		eventEarly.saveEventToCsv();

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);
		Object[][] infoEvents = eventSchedule.viewSchedule();
		int numberOfEvents = eventSchedule.getEvents().size();
		String lastEventName = (String) infoEvents[numberOfEvents - 1][0];

		Utility.deleteElementFromCsv(eventSchedulePath, eventEarly.getInformationAsArray());
		Utility.deleteElementFromCsv(eventSchedulePath, eventLate.getInformationAsArray());

		String eventLateName = eventLate.getName();

		assertTrue(eventLateName.equals(lastEventName));
	}
	/**
	 * tests if the selection of a single event within a certain period is done correctly
	 */
	public void testGetEventsWithinPeriod_OnlyOneEventIsSelected() {
		Event eventLate = new Event("Late Event", new DateHandler("01-01-2023"), pathAccesser);
		eventLate.setEventScheduleFile(eventSchedulePath);
		eventLate.saveEventToCsv();

		Event eventEarly = new Event("Early Event", new DateHandler("02-10-2022"), pathAccesser);
		eventEarly.setEventScheduleFile(eventSchedulePath);
		eventEarly.saveEventToCsv();

		DateHandler dateBegin = new DateHandler("01-10-2022");
		DateHandler dateEnd = new DateHandler("01-11-2022");

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);
		List<Event> eventsWithinPeriod = eventSchedule.GetEventsWithinPeriod(dateBegin, dateEnd);

		int numberSelectedEventsExpected = 1;

		assertTrue(numberSelectedEventsExpected == eventsWithinPeriod.size());
	}
	/**
	 * tests if the selection of multiple events within a certain time period is done correctly
	 */
	public void testGetEventsWithinPeriod_CorrectEventIsSelected() {
		Event eventLate = new Event("Late Event", new DateHandler("01-01-2023"), pathAccesser);
		eventLate.setEventScheduleFile(eventSchedulePath);
		eventLate.saveEventToCsv();

		Event eventEarly = new Event("Early Event", new DateHandler("02-10-2022"), pathAccesser);
		eventEarly.setEventScheduleFile(eventSchedulePath);
		eventEarly.saveEventToCsv();

		DateHandler dateBegin = new DateHandler("01-10-2022");
		DateHandler dateEnd = new DateHandler("01-11-2022");

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);
		List<Event> eventsWithinPeriod = eventSchedule.GetEventsWithinPeriod(dateBegin, dateEnd);

		Event eventSelected = eventsWithinPeriod.get(0);

		Arrays.equals(eventSelected.getInformationAsArray(), eventEarly.getInformationAsArray());
	}
	/**
	 * tests if the selection of events in the coming week is done correctly
	 */
	public void testGetEventsWithinPeriod_GetEventsOfTheComingWeek() {
		Event eventLate = new Event("Late Event", new DateHandler("01-01-2050"), pathAccesser);
		eventLate.setEventScheduleFile(eventSchedulePath);
		eventLate.saveEventToCsv();

		DateHandler currentDate = DateHandler.getCurrentDate();
		Event eventToday = new Event("Early Event", currentDate, pathAccesser);
		eventToday.setEventScheduleFile(eventSchedulePath);
		eventToday.saveEventToCsv();

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);
		List<Event> eventsWithinPeriod = eventSchedule.getEventsThisWeek();

		Event eventSelected = eventsWithinPeriod.get(0);

		Arrays.equals(eventSelected.getInformationAsArray(), eventToday.getInformationAsArray());
	}
	/**
	 * tests if events outside of the given time period are not present in the events of this week list
	 */
	public void testSetEvents_EventsOutsideWeekAreRemoved() {
		Event eventLate = new Event("Late Event", new DateHandler("01-01-2023"), pathAccesser);
		eventLate.setEventScheduleFile(eventSchedulePath);
		eventLate.saveEventToCsv();

		Event eventEarly = new Event("Early Event", new DateHandler("02-10-2022"), pathAccesser);
		eventEarly.setEventScheduleFile(eventSchedulePath);
		eventEarly.saveEventToCsv();

		DateHandler dateBegin = new DateHandler("01-10-2022");
		DateHandler dateEnd = new DateHandler("01-11-2022");

		EventSchedule eventSchedule = new EventSchedule(pathAccesser);
		List<Event> eventsWithinPeriod = eventSchedule.GetEventsWithinPeriod(dateBegin, dateEnd);
		
		eventSchedule.setEvents(eventsWithinPeriod);
		int numberSelectedEventsExpected = 1;

		assertTrue(numberSelectedEventsExpected == eventSchedule.getEvents().size());
	}

	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}

}
