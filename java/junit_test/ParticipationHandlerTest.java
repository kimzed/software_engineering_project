package junit_test;

import java.io.File;
import java.time.LocalDate;

import junit.framework.TestCase;
import structural.events.Event;
import structural.events.EventSchedule;
import structural.handlers.DateHandler;
import structural.handlers.ParticipationHandler;
import structural.members.Member;
/**
 * Junit test class which handles tests regarding the ParticipationHandler class
 * contains 1 private variable and 1 test
 * @author gianp
 *
 */
public class ParticipationHandlerTest extends TestCase {

	private FakePathsAccesser pathsAccesser = new FakePathsAccesser();

	@Override
	protected void setUp() {
		pathsAccesser.createPaths();
	}
	/**
	 * tests if, for a certain member, the correct event is extracted from the event list, 
	 * where the member is registered to attend
	 */
	public void testTableviewRegisteredEvents_GetCorrectEvent() {

		Member member = new Member("Jan Test", "21/08/1993", "1234", "1234");
		DateHandler dateEvent = new DateHandler("01-01-2023");
		Event testEvent = new Event("ParticipationTestEvent", dateEvent, pathsAccesser);
		testEvent.addParticipant(member);
		testEvent.saveEventToCsv();
		ParticipationHandler participationHandler = new ParticipationHandler(pathsAccesser);

		Object[][] events = participationHandler.tableviewRegisteredEvents(member, pathsAccesser);

		String nameTestEvent = events[0][0].toString();
		String trueNameTestEvent = testEvent.getName();

		testEvent.removeParticipant(member);

		assertEquals(nameTestEvent, trueNameTestEvent);

	}

	@Override
	protected void tearDown() {
		pathsAccesser.deletePaths();
	}

}
