package junit_test;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;
import structural.handlers.CsvReader;
import structural.handlers.CsvWriter;
/**
 * Junit test class which handles tests regarding the csv reader class
 * contains 1 private parameter and 1 test
 * @author gianp
 *
 */
public class CsvReaderTest extends TestCase {
	private FakePathsAccesser pathAccesser = new FakePathsAccesser();

	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests if a csv is correctly converted to a matrix,
	 * checks if the first element of the matrix is a name of a member
	 */
	public void testConvertCsvToMatrix_FirstMemberHasName() {
		
		boolean appendToFile = true;
		CsvWriter csvWriter = new CsvWriter(pathAccesser.getMembersPath(), appendToFile);
		String[] info = {"one", "line"};
		csvWriter.writeEntryToFile(info);
		
		String filePathMembers = pathAccesser.getMembersPath();
		CsvReader csvReader = null;
		csvReader = new CsvReader(filePathMembers);

		String[] lines = null;
		lines = csvReader.getLinesFromCsv();

		String firstLine = lines[0];
		assertFalse(firstLine.isEmpty());

	}

	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}
}
