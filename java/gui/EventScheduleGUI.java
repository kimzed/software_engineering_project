package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import junit_test.FakePathsAccesser;

import javax.swing.JOptionPane;

import structural.events.Event;
import structural.events.EventSchedule;
import structural.handlers.DateHandler;
import structural.handlers.ParticipationHandler;
import structural.handlers.PathsAccesser;
import structural.members.Member;

import java.awt.GridLayout;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventScheduleGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_eventschedule;
	private JScrollPane panel_3;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(Member member) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventScheduleGUI frame = new EventScheduleGUI(member);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public EventScheduleGUI(Member member) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		JButton btnRegisterToEvent = new JButton("Register for Event");
		btnRegisterToEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean eventIsSelected = table_eventschedule.getSelectedRow() != -1;
				if (eventIsSelected) {
					addMembertoEvent(member);
				}
			}
		});
		panel_1.add(btnRegisterToEvent);

		EventSchedule schedule = new EventSchedule(new PathsAccesser());
		Object[][] events = null;
		events = schedule.viewSchedule();
		String[] columnNames = new String[] { "Event name", "Date" };

		table_eventschedule = new JTable(events, columnNames);
		table_eventschedule.getTableHeader().setReorderingAllowed(false);
		table_eventschedule.getColumnModel().getColumn(1).setMaxWidth(100);
		table_eventschedule.getColumnModel().getColumn(0).setMaxWidth(400);
		table_eventschedule.setRowSelectionAllowed(true);

		panel_3 = new JScrollPane(table_eventschedule);
		panel_3.setPreferredSize(new Dimension(450, 160));
		panel.add(panel_3);
	}

	private void addMembertoEvent(Member member) {
		int row = table_eventschedule.getSelectedRow();
		String nameEvent = table_eventschedule.getModel().getValueAt(row, 0).toString();
		String dateEventString = table_eventschedule.getModel().getValueAt(row, 1).toString();
		DateHandler dateEvent = new DateHandler(dateEventString);
		Event selectedEvent = new Event(nameEvent, dateEvent, new PathsAccesser());
		if (selectedEvent.participantExistsAlready(member) == false) {
			selectedEvent.addParticipant(member);
			JOptionPane.showMessageDialog(contentPane, "You have successfully registered for this event!");
		} else {
			JOptionPane.showMessageDialog(contentPane, "You are already registered for this event!", " ",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void updatePanel() {
		EventSchedule schedule = new EventSchedule(new PathsAccesser());
		Object[][] events = null;
		events = schedule.viewSchedule();
		String[] columnNames = new String[] { "Event name", "Date" };
		AbstractTableModel model = (AbstractTableModel) table_eventschedule.getModel();
		table_eventschedule.setModel(model);

		JTable temporarytable = new JTable(events, columnNames);
		model.fireTableDataChanged();
		table_eventschedule.setModel(temporarytable.getModel());
		table_eventschedule.setAutoCreateRowSorter(true);
		table_eventschedule.getTableHeader().setReorderingAllowed(false);
		table_eventschedule.getColumnModel().getColumn(1).setMaxWidth(100);
		table_eventschedule.getColumnModel().getColumn(0).setMaxWidth(400);
		table_eventschedule.setRowSelectionAllowed(true);
		model.fireTableDataChanged();
		panel.remove(panel_3);

		panel_3 = new JScrollPane(table_eventschedule);
		panel_3.setPreferredSize(new Dimension(450, 370));
		panel.add(panel_3);

	}

	public JPanel getMainPanel() {
		return contentPane;
	}

}