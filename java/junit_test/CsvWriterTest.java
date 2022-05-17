package junit_test;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;
import structural.handlers.CsvReader;
import structural.handlers.CsvWriter;
import structural.members.Member;
/**
 * Junit test class which handles tests regarding the csvWriter class
 * contains 1 private parameter and 1 test
 * @author gianp
 *
 */
public class CsvWriterTest extends TestCase 
{
	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	
	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests if the addLine method correctly adds one line to the csv
	 */
	public void testAddLine_AddLineAddsOneLineToCsv() 
	{	
		String filePathMembers = pathAccesser.getMembersPath();
		
		CsvReader csvReaderBeforeWriting = null;
		csvReaderBeforeWriting = new CsvReader(filePathMembers);
		String[] linesBefore = null;
		linesBefore = csvReaderBeforeWriting.getLinesFromCsv();
		int initialNumberlines = linesBefore.length;
		
		
		CsvWriter csvWriter = null;
		boolean appendToFile = true;
		csvWriter = new CsvWriter(filePathMembers, appendToFile);
		Member fakeMember = new Member("newname", "02/08/2021", "fake_password", "fakeemail");
		
		String[] informationMember = fakeMember.getInformationAsArray();
		csvWriter.writeEntryToFile(informationMember);
		
		CsvReader csvReaderAfterWriting = null;
		csvReaderAfterWriting = new CsvReader(filePathMembers);
		String[] linesAfter = null;
		linesAfter = csvReaderAfterWriting.getLinesFromCsv();
		int finalNumberlines = linesAfter.length;
		
		assertEquals(initialNumberlines+1, finalNumberlines);
	}
	
	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}
}
