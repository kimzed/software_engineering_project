package structural.members;

import java.io.IOException;
import java.time.LocalDate;

import structural.events.Event;
import structural.handlers.DateHandler;
import structural.handlers.PathsAccesser;

/**
 * public subclass of member which creates a boardmember object and is able to
 * create events contains 3 methods
 * 
 * @author gianp
 *
 */
public class BoardMember extends Member {
	/**
	 * public method which gets credentials and information of the member superclass
	 * 
	 * @param member
	 */
	public BoardMember(Member member) {
		super(member.getName(), member.getDateOfBirth(), member.getPassword(), member.getEmail());
	}

	/**
	 * public method which uses the credentials and creates a member, which is then
	 * turned into a board member
	 * 
	 * @param name
	 * @param dateOfBirth
	 * @param password
	 * @param email
	 */
	public BoardMember(String name, String dateOfBirth, String password, String email) {
		super(name, dateOfBirth, password, email);
	}

	/**
	 * public method which creates events using name and date
	 * 
	 * @param nameEvent
	 * @param dateEvent
	 * @return
	 * @throws IOException
	 */
	public Event createEvent(String nameEvent, DateHandler dateEvent) {

		Event event = new Event(nameEvent, dateEvent, new PathsAccesser());
		return event;
	}

}