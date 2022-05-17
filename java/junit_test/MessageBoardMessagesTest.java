package junit_test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import junit.framework.TestCase;
import structural.events.Event;
import structural.handlers.CsvReader;
import structural.handlers.CsvWriter;
import structural.handlers.MessageBoardManager;
import structural.handlers.Utility;
import structural.members.Member;
/**
 * Junit test class which handles tests regarding the messageBoardMessages class
 * contains 2 private parameters and 1 test
 * @author gianp
 *
 */
public class MessageBoardMessagesTest extends TestCase {
	private FakePathsAccesser pathAccesserTest = new FakePathsAccesser();
	private String filePath = pathAccesserTest.getBoardMessagesPath();
	
	@Override
	protected void setUp() {
		pathAccesserTest.createPaths();
	}
	/**
	 * tests if a newly created message is correctly written to the csv
	 */
	public void testWriteBoardMessages_MessagesWrittenToCsv() {

		CsvReader csvReaderStart = new CsvReader(filePath);
		int numberOfMessageBefore = Utility.getNumberOfRowsCsv(filePath);
		String message = "This is a test";

		MessageBoardManager messenger = new MessageBoardManager(new FakePathsAccesser(),new Member("Jan Lid", "12/23/2334", "qwerty", "email"));
		messenger.writeToMessageBoard(message);

		CsvReader csvReaderFinal = new CsvReader(filePath);
		String[] linesFinal = csvReaderFinal.getLinesFromCsv();
		int numberOfMessageAfter = Utility.getNumberOfRowsCsv(filePath);

		assertEquals(numberOfMessageBefore + 1, numberOfMessageAfter);
	}
	
	@Override
	protected void tearDown() {
		pathAccesserTest.deletePaths();
	}
}