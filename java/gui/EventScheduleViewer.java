package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

import structural.events.EventSchedule;
import structural.handlers.CsvReader;
import structural.handlers.PathsAccesser;

import java.awt.Point;
import java.io.IOException;
import java.text.ParseException;

public class EventScheduleViewer extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventScheduleViewer frame = new EventScheduleViewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public EventScheduleViewer() throws IOException, ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		EventSchedule schedule = new EventSchedule(new PathsAccesser());
		Object[][] events = schedule.viewSchedule();
		String[] columnNames = new String[]{"Event name", "Date"};
		
		table = new JTable(events, columnNames);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBackground(Color.LIGHT_GRAY);
		contentPane.add(table);
	}
}
