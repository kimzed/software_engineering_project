package structural.handlers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import structural.events.Event;
import structural.events.EventSchedule;
import structural.members.Member;

/**
 * public class which handles the amount of events a member has registered for
 * contains 1 private parameter and 3 methods
 * 
 * @author gianp
 *
 */
public class ParticipationHandler {
	private String filePath;

	/**
	 * public method which sets the path to the path containing the eventschedule
	 * 
	 * @param databaseAccesses
	 */
	public ParticipationHandler(DatabaseAccesses databaseAccesses) {
		this.filePath = databaseAccesses.getEventSchedulePath();
	}

	/**
	 * public method which shows a member's registered events in a tabular view uses
	 * member object and path as inputs if no registrations are made, creates a
	 * empty object if registrations are made, creates a table with event name and
	 * date
	 * 
	 * @param member
	 * @param databaseAccesses
	 * @return
	 */
	public Object[][] tableviewRegisteredEvents(Member member, DatabaseAccesses databaseAccesses) {
		String memberEmail = member.getEmail();
		int numberOfRegisteredEvents = 0;
		EventSchedule eventSchedule = new EventSchedule(databaseAccesses);
		List<Event> eventList = eventSchedule.getEvents();
		List<Event> eventsRegistered = new ArrayList<Event>();

		for (int i_events = 0; i_events < eventList.size(); i_events++) {
			Event eventFromList = eventList.get(i_events);

			eventFromList.readParticipantsEmail();

			List<String> emailsParticipants = eventFromList.getParticipantEmails();

			for (int i_emails = 0; i_emails < emailsParticipants.size(); i_emails++) {

				String emailFromList = emailsParticipants.get(i_emails);
				boolean memberIsInEvent = memberEmail.equals(emailFromList);
				if (memberIsInEvent) {
					eventsRegistered.add(eventFromList);
					numberOfRegisteredEvents++;
				}
			}
		}
		if (numberOfRegisteredEvents == 0) {
			Object[][] eventsRegisteredTable = new Object[0][2];
			return eventsRegisteredTable;
		} else {
			Object[][] eventsRegisteredTable = new Object[numberOfRegisteredEvents][2];
			for (int i_eventsReg = 0; i_eventsReg < eventsRegistered.size(); i_eventsReg++) {
				Event event = eventsRegistered.get(i_eventsReg);
				String name = event.getName();
				LocalDate date = event.getDate();
				DateHandler dateHandler = new DateHandler(date);

				eventsRegisteredTable[i_eventsReg][0] = name;
				eventsRegisteredTable[i_eventsReg][1] = dateHandler.getRawDate();
			}
			return eventsRegisteredTable;
		}

	}

}