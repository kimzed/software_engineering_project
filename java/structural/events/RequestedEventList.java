package structural.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import structural.handlers.CsvReader;
import structural.handlers.DatabaseAccesses;
import structural.handlers.DateHandler;
import structural.handlers.PathsAccesser;
import structural.handlers.Utility;
import structural.members.Member;
import structural.members.MemberList;

/**
 * public class which handles the list of requested events
 *  contains 3 private parameters and 3 methods
 * @author gianp
 *
 */
public class RequestedEventList {
	private String filePath;
	private List<RequestedEvent> requestedEvents;
	private DatabaseAccesses databaseAccesses;

	/**
	 * public method which sets the path to the location of requested events csv
	 * also calls the readEvents method to obtain the requested events
	 * 
	 * @param databaseAccesses
	 */
	public RequestedEventList(DatabaseAccesses databaseAccesses) {
		this.databaseAccesses = databaseAccesses;
		this.filePath = databaseAccesses.getRequestedEventsPath();

		readEvents();
	}

	/**
	 * getter for the requested events
	 * 
	 * @return
	 */
	public List<RequestedEvent> getRequestedEvents() {
		return requestedEvents;
	}

	/**
	 * private method which reads the current requested events from the csv and
	 * extracts them into an arrayList
	 */
	private void readEvents() {
		requestedEvents = new ArrayList<RequestedEvent>();
		CsvReader csvReader = new CsvReader(filePath);
		List<String[]> eventsRows = csvReader.getElementsFromCsv();

		for (int i_event = 0; i_event < eventsRows.size(); i_event++) {
			String[] tokens = eventsRows.get(i_event);
			String name = (tokens[0]);
			String rawDate = (tokens[1]);

			String memberEmail = (tokens[2]);
			MemberList members = new MemberList(new PathsAccesser());
			members.readMembers();
			Member member = members.getMemberBasedOnEmail(memberEmail);
			String eventExplanation = (tokens[3]);
			DateHandler date = new DateHandler(rawDate);
			RequestedEvent event = new RequestedEvent(name, date, member, eventExplanation, databaseAccesses);
			requestedEvents.add(event);
		}

	}

}
