package junit_test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import structural.handlers.CsvReader;
import structural.handlers.PathsAccesser;
import structural.members.BoardMemberList;
import structural.members.Member;

/**
 * JUnit test class handling tests regarding the boardmember list class
 * contains 3 private parameters and 4 tests
 * @author gianp
 *
 */
public class BoardMemberListTest extends TestCase {

	private FakePathsAccesser pathAccessor = new FakePathsAccesser();
	private String filePathBoardMembers = pathAccessor.getBoardMemberListPath();
	private Member testMember = new Member("Test Man", "12-12-2010", "testPassword", "test@test.test");

	@Override
	protected void setUp() {
		pathAccessor.createPaths();
	}
	/**
	 * tests if an element is correctly saved in the boardmember list test csv
	 */
	public void testReadBoardMember_ElementInListOfBoardMembers() {

		BoardMemberList boardMemberList = new BoardMemberList(pathAccessor);
		boardMemberList.addMember(testMember);
		boardMemberList.saveMembers();
		boardMemberList.readMembers();

		List<Member> boardMembers = boardMemberList.getMembers();
		Member firstMember = boardMembers.get(0);
		String name = firstMember.getName();
		assertFalse(name.isEmpty());
	}
	/**
	 * tests if the number of board members is correct in the boardmember list csv
	 */
	public void testReadBoardMember_NumberOfBoardMembersIsCorrect() {

		BoardMemberList boardMemberList = new BoardMemberList(pathAccessor);
		boardMemberList.addMember(testMember);
		boardMemberList.saveMembers();
		boardMemberList.readMembers();
		CsvReader csvReader = null;
		csvReader = new CsvReader(filePathBoardMembers);
		String[] lines = null;
		lines = csvReader.getLinesFromCsv();
		int numberlines = lines.length;
		List<Member> boardmembers = boardMemberList.getMembers();
		assertEquals(numberlines, boardmembers.size());

	}
	/**
	 * tests if duplicates are not allowed in the csv,
	 * if duplicate value is given, an error message is shown
	 */
	public void testBoardMemberAlreadyExists_duplicateThrowsCorrectException() {

		BoardMemberList boardMemberList = new BoardMemberList(pathAccessor);
		boardMemberList.addMember(testMember);
		boardMemberList.saveMembers();
		boardMemberList.readMembers();
		CsvReader csvReader = new CsvReader(filePathBoardMembers);
		List<Member> members = boardMemberList.members;

		try {
			boardMemberList.addMember(members.get(0));
		} catch (IllegalArgumentException e) {
			String errorMessage = "Member already exists in the database";
			assertEquals(errorMessage, e.getMessage());
		}
	}
	/**
	 * tests if the number of boardmembers is correct in the database csv
	 */
	public void testSaveMembers_NumberOfBoardMemberIsCorrect() {
		BoardMemberList boardMemberList = new BoardMemberList(pathAccessor);
		boardMemberList.addMember(testMember);
		boardMemberList.saveMembers();
		boardMemberList.readMembers();
		int numberOfMembers = boardMemberList.members.size();

		boardMemberList.saveMembers();

		boardMemberList = null;

		BoardMemberList boardMemberListAfterSave = new BoardMemberList(pathAccessor);
		boardMemberListAfterSave.readMembers();
		int numberOfMembersAfterSave = boardMemberListAfterSave.members.size();

		assertEquals(numberOfMembers, numberOfMembersAfterSave);
	}

	@Override
	protected void tearDown() {
		pathAccessor.deletePaths();
	}

}
