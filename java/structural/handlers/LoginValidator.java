package structural.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import structural.members.BoardMemberList;
import structural.members.Member;
import structural.members.MemberList;
/**
 * public class which handles the validation of login parameters given in the GUI
 * checks the input with the known database and returns if the parameters are correct
 * contains 3 private parameters and 5 methods
 * @author gianp
 *
 */
public class LoginValidator {
	
	private String adminEmail = "admin";
	private String adminPassword =  "admin";
	private DatabaseAccesses databaseAccesses;

	/**
	 * public method which sets the filepath to the path where memberlist is located
	 * @param databaseAccesses
	 */
	public LoginValidator(DatabaseAccesses databaseAccesses)
	{
		this.databaseAccesses = databaseAccesses;
	}
	/**
	 * public method which validates the login of an administrator
	 * uses email and password and checks the administrator database if these are known
	 * returns a boolean value indicating if correct or not
	 * @param inputEmail
	 * @param password
	 * @return
	 */
	public boolean validateAdminLogin(String inputEmail, char[] password) {
		boolean isCorrect = false;
		String inputPassword = String.valueOf(password);
		if(inputEmail.equals(adminEmail) && inputPassword.equals(adminPassword))  {
			isCorrect = true;
			}
		else {
			isCorrect = false;}
		
		return isCorrect;
		

	}
	/**
	 * public method which validates the login credentials of members
	 * uses email and password as input
	 * returns boolean values for email and password indicating if these are present in the member database
	 * @param inputEmail
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public boolean validateLoginMembers(String inputEmail, char[] password) {

		String inputPassword = String.valueOf(password);
		MemberList members = new MemberList(databaseAccesses);
		members.readMembers();
		try 
		{
			Member member = members.getMemberBasedOnEmail(inputEmail);
			boolean passwordIsCorrect = member.passwordIsCorrect(inputPassword);
			return passwordIsCorrect;
		}
		catch(NoSuchElementException e)
		{
			boolean emailIsCorrect = false;
			return emailIsCorrect;
		}
	}   
	/**
	 * public method which validates the login credentials of boardmembers
	 * uses email and password as input
	 * returns boolean values for email and password indicating if these are present in the boardmember database
	 * @param inputEmail
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public boolean validateLoginBoardMembers(String inputEmail, char[] password) {

		String inputPassword = String.valueOf(password);
		BoardMemberList boardMembers = new BoardMemberList(databaseAccesses);
		boardMembers.readMembers();
		try 
		{
			Member member = boardMembers.getMemberBasedOnEmail(inputEmail);
			boolean passwordIsCorrect = member.passwordIsCorrect(inputPassword);
			return passwordIsCorrect;
		}
		catch(NoSuchElementException e)
		{
			boolean emailIsCorrect = false;
			return emailIsCorrect;
		}
	}

}
