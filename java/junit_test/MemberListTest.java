package junit_test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import junit.framework.TestCase;
import structural.handlers.CsvReader;
import structural.members.Member;
import structural.members.MemberList;
	/**
	 * Junit test class which handles tests regarding the memberlist class
	 * contains 3 private variables and 7 tests
	 * @author gianp
	 *
	 */
public class MemberListTest extends TestCase {

	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	private Member testMember = new Member("Test Man", "12-12-2010", "testPassword", "test@test.test");
	private String memberListPath = pathAccesser.getMembersPath();

	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests if a member is read correctly from the test csv
	 */
	public void testReadMember_ElementInListOfMembers() {
		MemberList memberList = new MemberList(pathAccesser);
		memberList.addMember(testMember);
		memberList.readMembers();

		List<Member> members = memberList.getMembers();
		Member firstMember = members.get(0);
		String name = firstMember.getName();
		assertFalse(name.isEmpty());
	}
	/**
	 * tests if the number of members is read correctly from the csv
	 */
	public void testReadMember_NumberOfMembersIsCorrect() {
		MemberList memberList = new MemberList(pathAccesser);
		memberList.addMember(testMember);
		memberList.readMembers();
		CsvReader csvReader = null;

		csvReader = new CsvReader(memberListPath);
		String[] lines = null;
		lines = csvReader.getLinesFromCsv();
		int numberlines = lines.length;
		List<Member> members = memberList.getMembers();
		assertEquals(numberlines, members.size());

	}
	/**
	 * tests if duplicates are not allowed in the test memberlist
	 */
	public void testMemberAlreadyExists_duplicateThrowsCorrectException() {

		MemberList memberList = new MemberList(pathAccesser);
		memberList.addMember(testMember);
		memberList.readMembers();
		CsvReader csvReader = new CsvReader(memberListPath);
		List<Member> members = memberList.members;

		try {
			memberList.addMember(members.get(0));
		} catch (IllegalArgumentException e) {
			String errorMessage = "Member already exists in the database";
			assertEquals(errorMessage, e.getMessage());
		}
	}
	/**
	 * tests if a member is removed correctly from the test memberlist csv
	 */
	public void testRemoveMember_OneElementIsRemoved() {
		MemberList memberList = new MemberList(pathAccesser);
		memberList.addMember(testMember);
		memberList.readMembers();

		Member memberToDelete = new Member("Jan", "25/04/1995", "fake_password", "email");
		memberList.addMember(memberToDelete);
		int numberOfMembers = memberList.members.size();
		memberList.removeMember(memberToDelete);
		int newNumberOfMembers = memberList.members.size();

		assertEquals(numberOfMembers - 1, newNumberOfMembers);

	}
	/**
	 * tests if new members are correctly saved to csv
	 */
	public void testSaveMembers_NumberOfMemberIsCorrect() {
		MemberList memberList = new MemberList(pathAccesser);
		memberList.addMember(testMember);
		memberList.readMembers();
		int numberOfMembers = memberList.members.size();

		memberList.saveMembers();

		memberList = null;

		MemberList memberListAfterSave = new MemberList(pathAccesser);
		memberListAfterSave.readMembers();
		int numberOfMembersAfterSave = memberListAfterSave.members.size();

		assertEquals(numberOfMembers, numberOfMembersAfterSave);
	}
	/**
	 * tests if a given email which is not present in the database, is not recognized correctly
	 */
	public void testGetMemberBasedOnEmail_ReturnsCorrectExceptionIfEmailIsIncorrect() {
		MemberList memberList = new MemberList(pathAccesser);
		memberList.addMember(testMember);

		try {
			memberList.getMemberBasedOnEmail("this email doesnt exist");
		} catch (NoSuchElementException e) {
			String errorMessage = "Email does not exist in the database";
			assertEquals(errorMessage, e.getMessage());
		}
	}
	/**
	 * tests if a given email which is present in the database is recognized correctly
	 */
	public void testGetMemberBasedOnEmail_ReturnsMemberWhenEmailExists() {
		MemberList memberList = new MemberList(pathAccesser);
		memberList.addMember(testMember);

		Member memberFound = memberList.getMemberBasedOnEmail("test@test.test");

		Arrays.equals(memberFound.getInformationAsArray(), testMember.getInformationAsArray());
	}

	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}

}