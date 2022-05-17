package structural.members;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import structural.handlers.CsvReader;
import structural.handlers.CsvWriter;
import structural.handlers.DatabaseAccesses;
import structural.handlers.Utility;

/**
 * public class which handles the member list contains 1 private and 1 public
 * parameter and 12 methods
 * 
 * @author gianp
 *
 */
public class MemberList {

	private static String filePath;
	public List<Member> members;

	/**
	 * public method which sets the path to the location where member list is
	 * located also creates a new arraylist of members
	 * 
	 * @param databaseAccesses
	 */
	public MemberList(DatabaseAccesses databaseAccesses) {
		this.filePath = databaseAccesses.getMembersPath();
		members = new ArrayList<Member>();
	}

	/**
	 * public method to read members from the memberlist csv file
	 */
	public void readMembers() // TODO this method should not read passwords
	{
		CsvReader csvReader = new CsvReader(this.filePath);
		List<String[]> membersRows = csvReader.getElementsFromCsv();
		members = new ArrayList<Member>();

		for (int i_member = 0; i_member < membersRows.size(); i_member++) {
			String[] tokens = membersRows.get(i_member);
			String name = (tokens[0]);
			String dateofbirth = (tokens[1]);
			String password = (tokens[2]);
			String email = (tokens[3]);
			Member newmember = new Member(name, dateofbirth, password, email);
			members.add(newmember);
		}
	}

	/**
	 * getter for members, returns members in a list
	 * 
	 * @return
	 */
	public List<Member> getMembers() {
		return members;
	}

	/**
	 * public method which adds a new member to the member list returns error if the
	 * member already exists in the database
	 * 
	 * @param member
	 * @throws IllegalArgumentException
	 */
	public void addMember(Member member) throws IllegalArgumentException {
		if (members.size() == 0) {
			members.add(member);
			saveMembers();
		} else if (memberAlreadyExists(member)) {
			throw new IllegalArgumentException("Member already exists in the database");

		} else {
			members.add(member);
			saveMembers();
		}
	}

	/**
	 * public method to remove a member from the memberlist returns error if the
	 * member does not exist in the database
	 * 
	 * @param member
	 * @throws NoSuchElementException
	 */
	public void removeMember(Member member) throws NoSuchElementException {
		if (memberAlreadyExists(member)) {
			int indexMember = getIndexMemberBasedOnValue(member.getInformationAsArray());
			Member memberToDelete = members.get(indexMember);
			members.remove(memberToDelete);
		} else {
			throw new NoSuchElementException("Member does not exists in the database");
		}
	}

	/**
	 * private method to check if a member exists in the database returns a boolean
	 * value indicating if the member exists in the database
	 * 
	 * @param member
	 * @return
	 */
	private boolean memberAlreadyExists(Member member) {

		return Utility.elementAlreadyExists(member, members);
	}

	/**
	 * private method to get the index of a member in the database based on given
	 * value returns the index of the given member
	 * 
	 * @param informationMember
	 * @return
	 */
	private int getIndexMemberBasedOnValue(String[] informationMember) {

		return Utility.getIndexfromElement(members, informationMember);

	}

	/**
	 * public method to save member to the memberlist
	 */
	public void saveMembers() {
		boolean appendToFile = false;
		CsvWriter csvWriter = new CsvWriter(filePath, appendToFile);
		ArrayList<String[]> informationMembers = getMembersAsStringArrays();
		csvWriter.writeEntriesToFile(informationMembers);
	}

	/**
	 * protected method to get an string array of the members returns a string array
	 * containing information of members
	 * 
	 * @return
	 */
	protected ArrayList<String[]> getMembersAsStringArrays() {
		ArrayList<String[]> informationMembers = new ArrayList<String[]>();

		for (int i_member = 0; i_member < members.size(); i_member++) {
			Member memberFromList = members.get(i_member);
			String[] informationMember = memberFromList.getInformationAsArray();
			informationMembers.add(informationMember);
		}

		return informationMembers;
	}

	/**
	 * public method to extract a member from the member list based on email returns
	 * the member which has corresponding email adress
	 * 
	 * @param email
	 * @return
	 */
	// TODO test this
	public Member getMemberBasedOnEmail(String email) {
		Member member;
		try {
			int indexMember = getIndexMemberBasedOnEmail(email);
			member = members.get(indexMember);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException("Email does not exist in the database");
		}

		return member;
	}

	/**
	 * private method to get the index of a member based on email adress returns the
	 * index of the member which corresponds to the given email adress
	 * 
	 * @param email
	 * @return
	 */
	private int getIndexMemberBasedOnEmail(String email) {
		for (int i_member = 0; i_member < members.size(); i_member++) {
			Member memberFromList = members.get(i_member);
			String emailMember = memberFromList.getEmail();
			boolean emailsMatch = emailMember.equals(email);

			if (emailsMatch) {
				int indexMember = members.indexOf(memberFromList);
				return indexMember;
			}
		}
		return -1;
	}

	/**
	 * public method to extract credentials of all members in the member list
	 * returns the login credentials of the given member
	 * 
	 * @return
	 */
	public String[][] getCredentials() { // TODO this method is the only one that will read passwords
		int numberMembers = members.size();
		String[][] credentials = new String[numberMembers][2];

		for (int i_member = 0; i_member < numberMembers; i_member++) {
			Member member = members.get(i_member);
			String email = member.getEmail();
			String password = member.getPassword();
			credentials[i_member][0] = email;
			credentials[i_member][1] = password;
		}

		return credentials;
	}
}
