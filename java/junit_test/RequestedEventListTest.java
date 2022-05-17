package junit_test;

import java.util.List;

import junit.framework.TestCase;
import structural.events.RequestedEvent;
import structural.events.RequestedEventList;
import structural.handlers.DateHandler;
import structural.handlers.PathsAccesser;
import structural.members.Member;
/**
 * Junit test class which handles the tests regarding the RequestedEventList class
 * contains 2 private parameters and 2 tests
 * @author gianp
 *
 */
public class RequestedEventListTest extends TestCase {

	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	private String requestedEventSchedulePath = pathAccesser.getRequestedEventsPath();

	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests if the requested event is correctly loaded into an information array
	 */
	public void testReadEvents_EventIsLoaded() {
		Member newmember = new Member("Jan", "12-12-2012", "password", "123");
		RequestedEvent testEvent = new RequestedEvent("course", new DateHandler("01-05-2024"), newmember, "test",
				pathAccesser);
		RequestedEventList reqEvents = new RequestedEventList(pathAccesser);
		List<RequestedEvent> reqEventsList = reqEvents.getRequestedEvents();
		RequestedEvent readEvent = reqEventsList.get(0);
		String[] testInfo = testEvent.getInformationAsArray();
		String[] readEventInfo = readEvent.getInformationAsArray();
		assertEquals(testInfo[0], readEventInfo[0]);

	}
	/**
	 * tests if a standard message is given when no custom message has been given
	 */
	public void testConstructor_NoDescriptionSavesMessage() {

		Member newmember = new Member("Jan", "12-12-2012", "password", "123");
		RequestedEvent reqevent = new RequestedEvent("course", new DateHandler("01-05-2024"), newmember, "",
				pathAccesser);

		RequestedEventList reqEvents = new RequestedEventList(pathAccesser);
		List<RequestedEvent> requestedEvents = reqEvents.getRequestedEvents();

		RequestedEvent loadedRequestedEvent = requestedEvents.get(0);
		String requestMessage = loadedRequestedEvent.getRequestMessage();

		assertTrue("No message found".equals(requestMessage));

	}

	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}

}
