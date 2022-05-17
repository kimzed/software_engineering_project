package junit_test;

import java.io.File;

import junit.framework.TestCase;
/**
 * Junit test file which handles the tests regarding the fakePathsAccesser class
 * contains 3 private parameters and 2 tests
 * @author gianp
 *
 */
public class FakePathsAccesserTest extends TestCase {

	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	private String eventParticipantsFolder = pathAccesser.getEventParticipantsFolderPath();
	private String membersPath = pathAccesser.getMembersPath();

	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tets if the test filePaths are created correctly
	 */
	public void testCreatePaths() {

		File membersFile = new File(membersPath);
		assertTrue(membersFile.exists());

	}
	/**
	 * tests if the test filePaths are deleted correctly
	 */
	public void testDeletePaths() {
		File membersFile = new File(membersPath);
		pathAccesser.deletePaths();

		assertFalse(membersFile.exists());
	}

	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}
}
