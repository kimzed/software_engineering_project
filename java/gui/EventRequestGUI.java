package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import structural.events.Event;
import structural.events.RequestedEvent;
import structural.handlers.DateHandler;
import structural.handlers.DateLabelFormatter;
import structural.handlers.PathsAccesser;
import structural.members.Member;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import javax.swing.JTextArea;

public class EventRequestGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDateOfEvent;
	private JTextField textFieldNameOfEvent;
	private JButton btnRequestNewEvent;
	private JLabel lblEventCreatedSuccesfully;
	private JLabel lblExplanationOfEvent;
	private JTextField textAreaExplanationOfEvent;

	/**
	 * Launch the application.
	 */
	public static void main(Member member) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventRequestGUI frame = new EventRequestGUI(member);
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
	public EventRequestGUI(Member member) {
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 232);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNameOfEvent = new JLabel("Name of event");
		GridBagConstraints gbc_lblNameOfEvent = new GridBagConstraints();
		gbc_lblNameOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOfEvent.anchor = GridBagConstraints.EAST;
		gbc_lblNameOfEvent.gridx = 1;
		gbc_lblNameOfEvent.gridy = 1;
		contentPane.add(lblNameOfEvent, gbc_lblNameOfEvent);

		textFieldNameOfEvent = new JTextField();
		GridBagConstraints gbc_textFieldNameOfEvent = new GridBagConstraints();
		gbc_textFieldNameOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNameOfEvent.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNameOfEvent.gridx = 2;
		gbc_textFieldNameOfEvent.gridy = 1;
		contentPane.add(textFieldNameOfEvent, gbc_textFieldNameOfEvent);
		textFieldNameOfEvent.setColumns(10);

		JLabel lblDateOfEvent = new JLabel("Date of Event");
		GridBagConstraints gbc_lblDateOfEvent = new GridBagConstraints();
		gbc_lblDateOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfEvent.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfEvent.gridx = 1;
		gbc_lblDateOfEvent.gridy = 2;
		contentPane.add(lblDateOfEvent, gbc_lblDateOfEvent);

		lblExplanationOfEvent = new JLabel("Explanation of Event");
		GridBagConstraints gbc_lblExplanationOfEvent = new GridBagConstraints();
		gbc_lblExplanationOfEvent.anchor = GridBagConstraints.EAST;
		gbc_lblExplanationOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblExplanationOfEvent.gridx = 1;
		gbc_lblExplanationOfEvent.gridy = 3;
		contentPane.add(lblExplanationOfEvent, gbc_lblExplanationOfEvent);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.insets = new Insets(0, 0, 5, 5);
		gbc_datePicker.gridx = 2;
		gbc_datePicker.gridy = 2;
		contentPane.add(datePicker, gbc_datePicker);

		textAreaExplanationOfEvent = new JTextField();
		GridBagConstraints gbc_textAreaExplanationOfEvent = new GridBagConstraints();
		gbc_textAreaExplanationOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaExplanationOfEvent.fill = GridBagConstraints.BOTH;
		gbc_textAreaExplanationOfEvent.gridx = 2;
		gbc_textAreaExplanationOfEvent.gridy = 3;
		contentPane.add(textAreaExplanationOfEvent, gbc_textAreaExplanationOfEvent);

		btnRequestNewEvent = new JButton("Request Event");
		GridBagConstraints gbc_btnRequestNewEvent = new GridBagConstraints();
		gbc_btnRequestNewEvent.insets = new Insets(0, 0, 5, 5);
		gbc_btnRequestNewEvent.gridx = 2;
		gbc_btnRequestNewEvent.gridy = 4;
		contentPane.add(btnRequestNewEvent, gbc_btnRequestNewEvent);

		btnRequestNewEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String eventDate = datePicker.getJFormattedTextField().getText();
				String eventExplanation = textAreaExplanationOfEvent.getText();
				String nameEvent = textFieldNameOfEvent.getText();
				boolean dateIsEmpty = eventDate.equals("");
				
				
				if (nameEvent.trim().isEmpty() || dateIsEmpty) {
					JOptionPane.showMessageDialog(contentPane, "You cannot request an event without a name or date",
							"Input error", JOptionPane.ERROR_MESSAGE);
				} else {
					
					if (eventExplanation.equals("")) {
						eventExplanation = "No message found";
					}
										try {
						DateHandler dateEvent = new DateHandler(eventDate);
						RequestedEvent Requestedevent = new RequestedEvent(nameEvent, dateEvent, member,
								eventExplanation, new PathsAccesser());
						String dateEventDisplay = Requestedevent.getInformationAsArray()[1];
						JOptionPane.showMessageDialog(contentPane,
								"You have requested the event: " + nameEvent + " on " + dateEventDisplay);
					} catch (DateTimeParseException d) {
						JOptionPane.showMessageDialog(contentPane, "Enter a date for the event", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(contentPane, "Error", "Error", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}

				}
			}
		});

		lblEventCreatedSuccesfully = new JLabel("");
		lblEventCreatedSuccesfully.setForeground(Color.GREEN);
		lblEventCreatedSuccesfully.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblEventCreatedSuccesfully = new GridBagConstraints();
		gbc_lblEventCreatedSuccesfully.insets = new Insets(0, 0, 0, 5);
		gbc_lblEventCreatedSuccesfully.gridx = 1;
		gbc_lblEventCreatedSuccesfully.gridy = 6;
		contentPane.add(lblEventCreatedSuccesfully, gbc_lblEventCreatedSuccesfully);
	}

	public JPanel getMainPanel() {
		return contentPane;
	}

}
