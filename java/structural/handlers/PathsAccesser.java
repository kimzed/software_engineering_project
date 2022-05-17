package structural.handlers;
/**
 * public class used for accessing different paths for different files
 * contains 7 private parameters and 7 methods
 * @author gianp
 *
 */
public class PathsAccesser implements DatabaseAccesses {

	private String eventSchedulePath = "data/EventSchedule/EventSchedule.csv";
	private String membersPath = "data/member_list.csv";
	private String eventParticipantsFolderPath = "data/EventMemberLists/";
	private String messagesFolderPath = "data/Member_messages/";
	private String boardMessagesPath = "data/Member_messages/MessageBoardMessages.csv";
	private String boardMemberListPath = "data/boardmember_list.csv";
	private String requestedEventsPath = "data/EventSchedule/RequestedEvents.csv";
	private String musicFilePath = "data/Song/009_Sound_System_-_Dreamscape.wav";
	/**
	 * getter for the event schedule file path
	 */
	public String getEventSchedulePath() {
		return eventSchedulePath;
	}
	/**
	 * getter for the memberlist file path
	 */
	public String getMembersPath() {
		return membersPath;
	}
	/**
	 * getter for the pariticpation folders of events file path
	 */
	public String getEventParticipantsFolderPath() {
		return eventParticipantsFolderPath;
	}
	/**
	 * getter for the messages file path
	 */
	public String getMessagesFolderPath() {
		return messagesFolderPath;
	}
	/**
	 * getter for the messages on the message board file path
	 */
	public String getBoardMessagesPath() {
		return boardMessagesPath;
	}
	/**
	 * getter for the boardmember list file path
	 */
	public String getBoardMemberListPath()
	{
		return boardMemberListPath;
	}
	/**
	 * getter for the requested events file path
	 */
	public String getRequestedEventsPath()
	{
		return requestedEventsPath;
	}
}
