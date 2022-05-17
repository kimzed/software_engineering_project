package structural.members;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import structural.handlers.CsvWriter;
import structural.handlers.StringInformationObject;

/**
 * public class which creates a member object along with getters and setters
 * contains 5 private parameters and 15 methods
 * 
 * @author gianp
 *
 */
public class Member implements Comparable<Member>, StringInformationObject {

	private String name;
	private String dateOfBirth;
	private String password;
	private String email;
	private List<String> recievedMessages = new ArrayList<String>();

	/**
	 * public method setting credentials of a member
	 * 
	 * @param name
	 * @param dateOfBirth
	 * @param password
	 * @param email
	 */
	public Member(String name, String dateOfBirth, String password, String email) {
		this.password = password;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	/**
	 * public method extracting the last received message and returns it
	 * 
	 * @return
	 */
	public String getLatestMessage() {
		int iLastMessage = recievedMessages.size() - 1;
		return recievedMessages.get(iLastMessage);
	}

	/**
	 * getter for member email
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setter for member email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * getter for member name
	 * 
	 * @return
	 */
	// Returns the string value for "name" variable
	public String getName() {
		return name;
	}

	/**
	 * setter for member name
	 * 
	 * @param newName
	 */
	// Replaces the "name" variable with a new string
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * setter for member date of birth
	 * 
	 * @param dateofbirth
	 */
	public void setDateOfBirth(String dateofbirth) {
		this.dateOfBirth = dateofbirth;
	}

	/**
	 * getter for member date of birth
	 * 
	 * @return
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * getter for member password
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setter for member password
	 * 
	 * @param newPassword
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	/**
	 * public method to check a given password with the passwords in the database
	 * returns boolean value indicating if password is correct
	 * 
	 * @param passwordToBeTested
	 * @return
	 */
	public boolean passwordIsCorrect(String passwordToBeTested) {
		return password.equals(passwordToBeTested);
	}

	/**
	 * getter for member information as array
	 */
	public String[] getInformationAsArray() {
		String[] memberInformation = { name, dateOfBirth, password, email };

		return memberInformation;
	}

	/**
	 * public method overriding the method to compare members with each other
	 */
	@Override
	public int compareTo(Member member) {
		// TODO: check if rate is null if this can happen in your code
		return name.compareTo(member.name); // comparing on rate attribute
	}
}