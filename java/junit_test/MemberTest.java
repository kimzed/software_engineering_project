package junit_test;

import junit.framework.TestCase;
import structural.members.Member;
/**
 * Junit test class which handles tests regarding the member class
 * contains 1 test
 * @author gianp
 *
 */
public class MemberTest extends TestCase {
	/**
	 * tests if a password is correctly assigned to a member
	 */
	public void testPasswordIsCorrect_ReturnsTrueWithCorrectPassword() {

		String passwordMember = "newpassword";
		Member newmember = new Member("Jan", "21/08/1993", passwordMember, "email");

		newmember.setPassword(passwordMember);
		assertTrue(newmember.passwordIsCorrect(passwordMember));

	}
}
