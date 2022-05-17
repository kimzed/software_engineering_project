package structural.handlers;

/**
 * public interface used for accessing different databases located on different paths
 * @author gianp
 *
 */
public interface DatabaseAccesses {
	public String getEventSchedulePath();

	public String getMembersPath();

	public String getEventParticipantsFolderPath();

	public String getMessagesFolderPath();
	
	public String getBoardMessagesPath();
	
	public String getBoardMemberListPath();
	
	public String getRequestedEventsPath();
}
