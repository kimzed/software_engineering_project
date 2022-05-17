package structural.members;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import structural.handlers.CsvReader;
import structural.handlers.CsvWriter;
import structural.handlers.DatabaseAccesses;
/**
 * public class which handles the board member list
 * contains 3 methods
 * @author gianp
 *
 */
public class BoardMemberList extends MemberList {

	String filePath;
	/**
	 * public method to set the board member list file path and read the boardmember
	 * @param databaseAccesses
	 */
	public BoardMemberList(DatabaseAccesses databaseAccesses) {
		super(databaseAccesses);
		this.filePath = databaseAccesses.getBoardMemberListPath();
		readMembers();
	}
	/**
	 * public method to read members from the boardmembers file
	 * 
	 */
	public void readMembers()
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
	 * public method to save members to the board members file
	 */
	public void saveMembers() {
		boolean appendToFile = false;
		CsvWriter csvWriter = new CsvWriter(filePath, appendToFile);
		ArrayList<String[]> informationMembers = getMembersAsStringArrays();
		csvWriter.writeEntriesToFile(informationMembers);
	}

}
