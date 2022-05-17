package junit_test;

import java.io.IOException;

import junit.framework.TestCase;
import structural.handlers.LoginValidator;
import structural.handlers.Utility;

/**
 * Junit test class which handles tests regarding the loginValidator class
 * contains 2 private parameters and 5 tests
 * 
 * @author gianp
 *
 */
public class LoginValidatorTest extends TestCase {

	private FakePathsAccesser pathAccesser = new FakePathsAccesser();
	private String fileMembers = pathAccesser.getMembersPath();
	private String fileBoardMembers = pathAccesser.getBoardMemberListPath();

	@Override
	protected void setUp() {
		pathAccesser.createPaths();

		String[] infoMember = { "jan", "fake_date", "fake_password", "janlid@wur.nl" };
		String[] infoboardMember = { "jan", "fake_date", "qwerty", "bram@mail.nl" };
		Utility.addRowToCsv(infoMember, fileMembers);
		Utility.addRowToCsv(infoboardMember, fileBoardMembers);

	}

	/**
	 * tests if the login validator is able to detect an incorrect password
	 */
	public void testValidateLoginMembers_IncorrectPasswordReturnsFalse() {
		LoginValidator loginValidator = new LoginValidator(pathAccesser);
		String inputEmail = "janlid@wur.nl";
		char[] inputPassword = { 'r', 'a', 'k', 'e', '_', 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' };
		boolean isCorrect = loginValidator.validateLoginMembers(inputEmail, inputPassword);
		assertEquals(isCorrect, false);
	}

	/**
	 * tests of the login validator is able to detect a correct password
	 */
	public void testValidateLoginMembers_CorrectPasswordReturnsTrue() {

		LoginValidator loginValidator = new LoginValidator(pathAccesser);
		String inputEmail = "janlid@wur.nl";
		char[] inputPassword = { 'f', 'a', 'k', 'e', '_', 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' };
		boolean isCorrect = loginValidator.validateLoginMembers(inputEmail, inputPassword);
		assertEquals(isCorrect, true);
	}

	/**
	 * tests is an admin is logged in correctly
	 */
	public void testValidateAdminLogin_CorrectPasswordReturnsTrue() {
		LoginValidator loginValidator = new LoginValidator(pathAccesser);
		String inputEmail = "admin";
		char[] inputPassword = { 'a', 'd', 'm', 'i', 'n' };
		boolean isCorrect = loginValidator.validateAdminLogin(inputEmail, inputPassword);
		assertEquals(isCorrect, true);
	}

	/**
	 * tests if an admin is not able to login when giving wrong credentials
	 */
	public void testValidateAdminLogin_WrongEmailReturnsCorrectException() {
		LoginValidator loginValidator = new LoginValidator(pathAccesser);
		String inputEmail = "admin";
		char[] inputPassword = { 'a', 'd', 'm', 'i', 'n' };
		try {
			boolean isCorrect = loginValidator.validateAdminLogin(inputEmail, inputPassword);
		} catch (IndexOutOfBoundsException e) {
			String errorMessage = "Email is not in the database";
			assertEquals(errorMessage, e.getMessage());
		}
	}

	/**
	 * tests if a boardmember can login correctly
	 */
	public void testvalidateLoginBoardMembers_CorrectPasswordReturnsTrue() {

		LoginValidator loginValidator = new LoginValidator(pathAccesser);
		String inputEmail = "bram@mail.nl";
		char[] inputPassword = { 'q', 'w', 'e', 'r', 't', 'y' };
		boolean isCorrect = loginValidator.validateLoginBoardMembers(inputEmail, inputPassword);
		assertEquals(isCorrect, true);
	}

	@Override
	protected void tearDown() {
		pathAccesser.deletePaths();
	}

}
