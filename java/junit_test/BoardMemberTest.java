package junit_test;

import junit.framework.TestCase;
import structural.members.BoardMember;
import structural.members.Member;
import structural.members.MemberList;

/**
 * Junit test class handling tests regarding the boardmember class
 * contains 1 private variable and 1 test
 * @author gianp
 *
 */
public class BoardMemberTest extends TestCase {
	
	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	
	
	@Override
	protected void setUp() {
		pathAccesser.createPaths();
	}
	/**
	 * tests is the boardmember correctly inherits the name of the promoted member
	 */
	public void testConstructor_BoardMemberHasSameNameAsMemberInherited() {
		MemberList listMembers = new MemberList(new FakePathsAccesser());
		listMembers.readMembers();
		
		String name = "JohnCena";
		String dateOfBirth = "25/04/1995";
		String password = "fake_password";
		String email = "johncena@WWE.com";
		
		Member member = new Member(name, dateOfBirth, password, email);

		BoardMember boardMember = new BoardMember(member);
		String nameBoardMember = boardMember.getName();
		assertTrue(nameBoardMember.equals(name));
	}
	
	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}
}
