package structural.handlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import structural.members.Member;
/**
 * public class which manages the messages shown on the message board
 * contains 4 private parameters and 3 methods
 * @author gianp
 *
 */
public class MessageBoardManager {
	private String memberName;
	private String message;
	private String[] messages;
	private String filePath;
	private Member member;
	/**
	 * public methods which sets the filepath to the board messages path
	 * @param databaseAccesses
	 */
	public MessageBoardManager(DatabaseAccesses databaseAccesses, Member member) {
		this.member = member;
		this.filePath = databaseAccesses.getBoardMessagesPath();
	 }
	/**
	 * public method which takes a message and writes it to the message board
	 * @param message
	 */
	public void writeToMessageBoard(String message) {
		String[] messageToSave = {message , member.getName()};
		Utility.addRowToCsv(messageToSave, filePath);
	}
	
	 
	 /**
	  * public method which reads the messages on the message board
	  */

	public List<String[]> readMessages() {
		CsvReader reader = new CsvReader(filePath);
		List<String[]> messages = reader.getElementsFromCsv();
		return messages;
	}
}
