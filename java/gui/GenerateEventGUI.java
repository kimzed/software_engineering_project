package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import structural.events.Event;
import structural.events.RequestedEvent;
import structural.events.RequestedEventList;
import structural.handlers.DateHandler;
import structural.handlers.DateLabelFormatter;
import structural.handlers.PathsAccesser;
import structural.members.Member;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class GenerateEventGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDateOfEvent;
	private JTextField textFieldNameOfEvent;
	private JButton btnGenerateNewEvent;
	private JLabel lblEventCreatedSuccesfully;
	private Component btnDeleteRequest;
	private JScrollPane scrollPane;
	private JLabel lblDateHidden;
	private DefaultListModel<String> listModel;
	private List<RequestedEvent> reqEvents;
	private RequestedEventList reqEventList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateEventGUI frame = new GenerateEventGUI();
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
	public GenerateEventGUI() {
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
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNameOfEvent = new JLabel("Name of event");
		GridBagConstraints gbc_lblNameOfEvent = new GridBagConstraints();
		gbc_lblNameOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOfEvent.anchor = GridBagConstraints.EAST;
		gbc_lblNameOfEvent.gridx = 3;
		gbc_lblNameOfEvent.gridy = 2;
		contentPane.add(lblNameOfEvent, gbc_lblNameOfEvent);

		textFieldNameOfEvent = new JTextField();
		GridBagConstraints gbc_textFieldNameOfEvent = new GridBagConstraints();
		gbc_textFieldNameOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNameOfEvent.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNameOfEvent.gridx = 4;
		gbc_textFieldNameOfEvent.gridy = 2;
		contentPane.add(textFieldNameOfEvent, gbc_textFieldNameOfEvent);
		textFieldNameOfEvent.setColumns(10);

		lblDateHidden = new JLabel("Date of Requested Event");
		GridBagConstraints gbc_lblDateHidden = new GridBagConstraints();
		gbc_lblDateHidden.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateHidden.anchor = GridBagConstraints.EAST;
		gbc_lblDateHidden.gridx = 3;
		gbc_lblDateHidden.gridy = 3;
		contentPane.add(lblDateHidden, gbc_lblDateHidden);
		lblDateHidden.setVisible(false);

		textFieldDateOfEvent = new JTextField();
		GridBagConstraints gbc_textFieldDateOfEvent = new GridBagConstraints();
		gbc_textFieldDateOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDateOfEvent.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDateOfEvent.gridx = 4;
		gbc_textFieldDateOfEvent.gridy = 3;
		contentPane.add(textFieldDateOfEvent, gbc_textFieldDateOfEvent);
		textFieldDateOfEvent.setColumns(10);
		textFieldDateOfEvent.setVisible(false);

		JLabel lblDateOfEvent = new JLabel("Date of Event");
		GridBagConstraints gbc_lblDateOfEvent = new GridBagConstraints();
		gbc_lblDateOfEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfEvent.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfEvent.gridx = 3;
		gbc_lblDateOfEvent.gridy = 4;
		contentPane.add(lblDateOfEvent, gbc_lblDateOfEvent);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.insets = new Insets(0, 0, 5, 5);
		gbc_datePicker.gridx = 4;
		gbc_datePicker.gridy = 4;
		contentPane.add(datePicker, gbc_datePicker);

		JButton btnDeleteRequest = new JButton("Reject Event");
		GridBagConstraints gbc_btnDeleteRequest = new GridBagConstraints();
		gbc_btnDeleteRequest.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteRequest.gridx = 0;
		gbc_btnDeleteRequest.gridy = 5;
		contentPane.add(btnDeleteRequest, gbc_btnDeleteRequest);

		btnGenerateNewEvent = new JButton("Generate new event");
		btnGenerateNewEvent.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnGenerateNewEvent = new GridBagConstraints();
		gbc_btnGenerateNewEvent.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerateNewEvent.gridx = 4;
		gbc_btnGenerateNewEvent.gridy = 5;
		contentPane.add(btnGenerateNewEvent, gbc_btnGenerateNewEvent);

		lblEventCreatedSuccesfully = new JLabel("");
		lblEventCreatedSuccesfully.setForeground(Color.GREEN);
		lblEventCreatedSuccesfully.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblEventCreatedSuccesfully = new GridBagConstraints();
		gbc_lblEventCreatedSuccesfully.insets = new Insets(0, 0, 0, 5);
		gbc_lblEventCreatedSuccesfully.gridx = 1;
		gbc_lblEventCreatedSuccesfully.gridy = 7;
		contentPane.add(lblEventCreatedSuccesfully, gbc_lblEventCreatedSuccesfully);

		this.listModel = new DefaultListModel<String>();
		this.reqEventList = new RequestedEventList(new PathsAccesser());
		this.reqEvents = new ArrayList<RequestedEvent>(reqEventList.getRequestedEvents());

		for (int i = 0; i < (reqEvents.size()); i++) {
			listModel.addElement(reqEvents.get(i).getName());
		}
		JList<String> list = new JList<String>(listModel);
		list.setVisible(true);

		scrollPane = new JScrollPane(list);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int index = list.getSelectedIndex();

				if (index >= 0 && index < reqEvents.size()) {
					String[] eventInfo = reqEvents.get(index).getInformationAsArray();
					String eventName = eventInfo[0];
					String eventDate = eventInfo[1];
					textFieldNameOfEvent.setText(eventName);
					textFieldDateOfEvent.setText(eventDate);
					textFieldDateOfEvent.setVisible(true);
					lblDateHidden.setVisible(true);
					lblDateOfEvent.setText("Override Date");

				} else {
					textFieldNameOfEvent.setText("");
					textFieldDateOfEvent.setText("");
				}
			}
		});

		btnGenerateNewEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String dateFieldText = textFieldDateOfEvent.getText();
				String datePickerText = datePicker.getJFormattedTextField().getText();
				if (dateFieldText.equals("")) {
					generateEvent();
				} else {
					if (!datePickerText.equals("")) {
						generateEvent();
					} else {
						generateEventUsingHiddenText();
					}
				}
				lblDateOfEvent.setText("Date of Event");
			}

			private void generateEventUsingHiddenText() {
				String nameEvent = textFieldNameOfEvent.getText();
				if (nameEvent.trim().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "You cannot create an event without a name!",
							"Naming error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						DateHandler dateHandler = new DateHandler(textFieldDateOfEvent.getText());
						Event event = new Event(nameEvent, dateHandler, new PathsAccesser());
						event.saveEventToCsv();
						removeRequestedEvent();
						reloadListModel();
						cleanGUI();
						JOptionPane.showMessageDialog(contentPane,
								"You have created the event: " + nameEvent + " on " + dateHandler.getRawDate());
					} catch (DateTimeParseException d) {
						JOptionPane.showMessageDialog(contentPane, "Enter a date for the event", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {

						JOptionPane.showMessageDialog(contentPane, "Error", "Error", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}

			private void generateEvent() {
				String nameEvent = textFieldNameOfEvent.getText();
				String eventDate = datePicker.getJFormattedTextField().getText();
				boolean dateIsEmpty = eventDate.equals("");
				if (nameEvent.trim().isEmpty() || dateIsEmpty) {
					JOptionPane.showMessageDialog(contentPane, "You cannot create an event without a name!",
							"Naming error", JOptionPane.ERROR_MESSAGE);
				} else {

					try {
						DateHandler dateHandler = new DateHandler(eventDate);
						Event event = new Event(nameEvent, dateHandler, new PathsAccesser());
						event.saveEventToCsv();
						removeRequestedEvent();
						reloadListModel();
						cleanGUI();
						JOptionPane.showMessageDialog(contentPane,
								"You have created the event: " + nameEvent + " on " + dateHandler.getRawDate());
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

		btnDeleteRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRequestedEvent();
				reloadListModel();
				cleanGUI();
				lblDateOfEvent.setText("Date of Event");
			}
		});

	}

	private void removeRequestedEvent() {
		String reqEventName = textFieldNameOfEvent.getText();

		for (int index = 0; index < reqEvents.size(); index++) {
			String[] eventInfo = reqEvents.get(index).getInformationAsArray();
			String eventName = eventInfo[0];
			if (reqEventName.equals(eventName)) {
				RequestedEvent reqEventToBeRemoved = reqEvents.get(index);
				reqEventToBeRemoved.declineRequest();

			}
		}

	}

	private void reloadListModel() {
		this.reqEventList = new RequestedEventList(new PathsAccesser());
		this.reqEvents = new ArrayList<RequestedEvent>(reqEventList.getRequestedEvents());
		listModel.removeAllElements();
		;
		for (int i = 0; i < (reqEvents.size()); i++) {
			listModel.addElement(reqEvents.get(i).getName());
		}
	}

	private void cleanGUI() {
		textFieldNameOfEvent.setText("");
		textFieldDateOfEvent.setText("");
		textFieldDateOfEvent.setVisible(false);
		lblDateHidden.setVisible(false);
	}

	public JPanel getMainPanel() {
		return contentPane;
	}
}
