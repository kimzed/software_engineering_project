package gui_welcomescreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import structural.events.Event;
import structural.handlers.DateHandler;
import structural.handlers.ParticipationHandler;
import structural.handlers.PathsAccesser;
import structural.members.Member;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class RegisteredEventsGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_eventschedule;
	private JScrollPane panel_3;
	private Member member;
	private JPanel panel;
	private JScrollPane panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(Member member) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisteredEventsGUI frame = new RegisteredEventsGUI(member);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisteredEventsGUI(Member member) throws IOException, ParseException {
		this.member = member;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);

		JButton btnNewButton = new JButton("Cancel registration for Event");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.weightx = 1.0;
		gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean eventIsSelected = table_eventschedule.getSelectedRow() != -1;
				if (eventIsSelected) {
					removeMemberFromEvent(member);
				}
				updateTable();
			}
		});
		contentPane.add(btnNewButton, gbc_btnNewButton);

		ParticipationHandler handler = new ParticipationHandler(new PathsAccesser());
		Object[][] events = null;
		events = handler.tableviewRegisteredEvents(member, new PathsAccesser());

		String[] columnNames = new String[] { "Event name", "Date" };

		table_eventschedule = new JTable(new DefaultTableModel(events, columnNames));
		table_eventschedule.setAutoCreateRowSorter(true);
		table_eventschedule.getTableHeader().setReorderingAllowed(false);
		table_eventschedule.getColumnModel().getColumn(1).setMaxWidth(100);
		table_eventschedule.getColumnModel().getColumn(0).setMaxWidth(400);
		table_eventschedule.setRowSelectionAllowed(true);

		panel_1 = new JScrollPane(table_eventschedule);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weighty = 1.0;
		gbc_panel_1.weightx = 1.0;
		gbc_panel_1.insets = new Insets(5, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 2;

		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setPreferredSize(new Dimension(450, 160));
	}

	public void updateTable() {
		ParticipationHandler handler = new ParticipationHandler(new PathsAccesser());
		Object[][] events = null;
		events = handler.tableviewRegisteredEvents(member, new PathsAccesser());
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
		contentPane.remove(panel_1);
		panel_1 = new JScrollPane(table_eventschedule);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weighty = 1.0;
		gbc_panel_1.weightx = 1.0;
		gbc_panel_1.insets = new Insets(5, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setPreferredSize(new Dimension(450, 160));

	}

	private void removeMemberFromEvent(Member member) {
		int row = table_eventschedule.getSelectedRow();
		String nameEvent = table_eventschedule.getModel().getValueAt(row, 0).toString();
		String dateEventString = table_eventschedule.getModel().getValueAt(row, 1).toString();
		DateHandler dateHandler = new DateHandler(dateEventString);

		Event selectedEvent = new Event(nameEvent, dateHandler, new PathsAccesser());
		if (!selectedEvent.participantExistsAlready(member)) {
			JOptionPane.showMessageDialog(contentPane, "You are not registered to this event THIS SHOULD NOT HAPPEN!!",
					" ", JOptionPane.ERROR_MESSAGE);
		} else {
			selectedEvent.removeParticipant(member);
			JOptionPane.showMessageDialog(contentPane, "You are no longer registered for this event");
		}

	}

	public JPanel getMainPanel() {
		return contentPane;
	}
}
