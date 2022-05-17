package junit_test;

import java.util.ArrayList;

import structural.handlers.DatabaseAccesses;
import structural.handlers.Utility;
/**
 * publuc class which handles the getters, creation and deletion of test filePaths
 * contains 7 private parameters and 9 tests
 * @author gianp
 *
 */
public class FakePathsAccesser implements DatabaseAccesses {
	
	private String eventSchedulePath = "data/unit_test/EventSchedule/EventSchedule.csv";
	private String membersPath = "data/unit_test/member_list_test.csv";
	private String eventParticipantsListsFolderPath = "data/unit_test/EventMemberLists/";
	private String messagesFolderPath = "data/unit_test/Member_messages/";
	private String boardMessagesPath = messagesFolderPath+"MessageBoardMessages.csv";
	private String boardMemberListPath = "data/unit_test/boardmember_list.csv";
	private String requestedEventsPath = "data/unit_test/EventSchedule/RequestedEvents.csv";
	/**
	 * getter for the test eventSchedulePath
	 */
	public String getEventSchedulePath() {
		return eventSchedulePath;
	}
	/**
	 * getter for the test membersPath
	 */
	public String getMembersPath() {
		return membersPath;
	}
	/**
	 * getter for the test eventParticipantsFolderPath
	 */
	public String getEventParticipantsFolderPath() {
		return eventParticipantsListsFolderPath;
	}
	/**
	 * getter for the test MessagesFolderPath
	 */
	public String getMessagesFolderPath() {
		return messagesFolderPath;
	}
	/**
	 * getter for the test BoardMessagesPath
	 */
	public String getBoardMessagesPath() {
		return boardMessagesPath;
	}
	/**
	 * getter for the test BoardMemberListPath
	 */
	public String getBoardMemberListPath() {
		return boardMemberListPath;
	}
	/**
	 * getter for the test RequestedEventsPath
	 */
	public String getRequestedEventsPath() {
		return requestedEventsPath;
	}
	/**
	 * method to create all the test filePath
	 */
	public void createPaths() {

		Utility.createFile(getEventSchedulePath());
		Utility.createFile(getMembersPath());
		Utility.createDirectory(getEventParticipantsFolderPath());
		Utility.createFile(getBoardMessagesPath());
		Utility.createFile(getBoardMemberListPath());
		Utility.createFile(getRequestedEventsPath());

	}
	/**
	 * method to delete all the test filePath
	 */
	public void deletePaths() {

		Utility.deleteFile(getEventSchedulePath());
		Utility.deleteFile(getMembersPath());
		Utility.deleteFile(getBoardMessagesPath());
		Utility.deleteFile(getBoardMemberListPath());
		Utility.deleteFile(getRequestedEventsPath());
		Utility.deleteFilesFromDirectory(getEventParticipantsFolderPath());
		Utility.deleteFilesFromDirectory(eventParticipantsListsFolderPath);

	}

}
