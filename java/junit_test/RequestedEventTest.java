package junit_test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;

import com.opencsv.exceptions.CsvException;

import junit.framework.TestCase;
import structural.events.RequestedEvent;
import structural.handlers.CsvReader;
import structural.handlers.DateHandler;
import structural.handlers.Utility;
import structural.members.Member;
/**
 * Junit test class which handles the tests regarding the requestedEvent class
 * contains 3 private parameters and 2 tests
 * @author gianp
 *
 */
public class RequestedEventTest extends TestCase {
	
	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	private String csvFilePath = pathAccesser.getRequestedEventsPath();
	private String eventScheduleFile = pathAccesser.getEventSchedulePath();

	
	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests if a requested event is correctly saved to the test requestedEvents csv
	 */
	public void testsaveIntoCsv_requestIsAdded() {
		
		int numberRowsBefore = Utility.getNumberOfRowsCsv(csvFilePath);
		Member newmember = new Member("Jan", "12-12-2012", "password", "email");
		
		new RequestedEvent("course", new DateHandler("01-01-2024"), newmember, "test", pathAccesser);
		
		int numberRowsAfter = Utility.getNumberOfRowsCsv(csvFilePath);
		assertEquals(numberRowsBefore+1, numberRowsAfter);
		
	}

	public void testDeclineRequest_requestIsDeleted() {
		/**
		 * tests if a rejected request is correctly deleted from the requestedEvents csv
		 */
		Member newmember = new Member("Jan", "12-12-2012", "password", "email");
		RequestedEvent reqevent = new RequestedEvent("course",  new DateHandler("01-05-2024"), newmember, "test", pathAccesser);

		int numberRequestsBefore = Utility.getNumberOfRowsCsv(csvFilePath);
		reqevent.declineRequest();
		
		int numberRequestsAfter = Utility.getNumberOfRowsCsv(csvFilePath);

		assertEquals(numberRequestsBefore-1, numberRequestsAfter);

	}
	
	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}
	


}
