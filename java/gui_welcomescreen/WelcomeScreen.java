package gui_welcomescreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import gui.GUIMain;
import structural.members.Member;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.io.IOException;
import java.text.ParseException;

public class WelcomeScreen extends JFrame {

	private JPanel contentPane;
	private PostMessageGUI messageBoardPanel;
	private RegisteredEventsGUI registeredEventsGUIGrid;
	private MessageDisplayGUI messageDisplay;
	private JPanel panel_messageDisplay;
	private Member member;
	private JPanel registeredEventsGUIPanel;
	private JPanel panel_regEvents;
	private GridBagConstraints gbc_panel_regEvents;
	private EventsThisWeekGui eventThisWeek;
	private JPanel panel_eventThisWeek;
	private JLabel lblReminder;
	private Font fontTitle;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain guiMain = new GUIMain();
					WelcomeScreen frame = new WelcomeScreen(guiMain);
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
	public WelcomeScreen(GUIMain guiMain) {
		this.member = guiMain.getLoggedMember();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblWelcomeMsg = new JLabel("Welcome to the application!");
		GridBagConstraints gbc_lblWelcomeMsg = new GridBagConstraints();
		gbc_lblWelcomeMsg.anchor = GridBagConstraints.WEST;
		gbc_lblWelcomeMsg.weightx = 0.5;
		gbc_lblWelcomeMsg.insets = new Insets(5, 5, 5, 5);
		gbc_lblWelcomeMsg.gridx = 1;
		gbc_lblWelcomeMsg.gridy = 1;
		contentPane.add(lblWelcomeMsg, gbc_lblWelcomeMsg);
		
		
		JPanel panel_messageBoard = new JPanel();
		GridBagConstraints gbc_panel_messageBoard = new GridBagConstraints();
		gbc_panel_messageBoard.weighty = 0.25;
		gbc_panel_messageBoard.weightx = 0.5;
		gbc_panel_messageBoard.insets = new Insets(5, 5, 5, 5);
		gbc_panel_messageBoard.fill = GridBagConstraints.BOTH;
		gbc_panel_messageBoard.gridx = 1;
		gbc_panel_messageBoard.gridy = 5;
		contentPane.add(panel_messageBoard, gbc_panel_messageBoard);
		
		messageBoardPanel = new PostMessageGUI(member,this);
		JPanel messageBoardPanelGUI = messageBoardPanel.getMainPanel();
		panel_messageBoard.add(messageBoardPanelGUI);
		
		messageDisplay = new MessageDisplayGUI(this);
		
		panel_messageDisplay = new JPanel();
		panel_messageDisplay = messageDisplay.getMainPanel();
		panel_messageDisplay.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.LOWERED),
				"Messages", TitledBorder.RIGHT, TitledBorder.TOP, fontTitle,
				Color.white));
		GridBagConstraints gbc_panel_messageDisplay = new GridBagConstraints();
		gbc_panel_messageDisplay.weighty = 1.0;
		gbc_panel_messageDisplay.weightx = 0.5;
		gbc_panel_messageDisplay.insets = new Insets(5, 5, 5, 5);
		gbc_panel_messageDisplay.fill = GridBagConstraints.BOTH;
		gbc_panel_messageDisplay.gridx = 1;
		gbc_panel_messageDisplay.gridy = 2;
		contentPane.add(panel_messageDisplay, gbc_panel_messageDisplay);
		
		
		panel_regEvents = new JPanel();
		try {
			registeredEventsGUIGrid = new RegisteredEventsGUI(member);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		registeredEventsGUIPanel = registeredEventsGUIGrid.getMainPanel();
		
		panel_regEvents.add(registeredEventsGUIPanel);
		panel_regEvents.setSize(400, 400);
		panel_regEvents.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.LOWERED),
				"You are currently registered for these events", TitledBorder.CENTER, TitledBorder.TOP, fontTitle,
				Color.white));
		
		gbc_panel_regEvents = new GridBagConstraints();
		gbc_panel_regEvents.weighty = 1.0;
		gbc_panel_regEvents.weightx = 1.0;
		gbc_panel_regEvents.insets = new Insets(5, 5, 5, 5);
		gbc_panel_regEvents.fill = GridBagConstraints.BOTH;
		gbc_panel_regEvents.gridx = 3;
		gbc_panel_regEvents.gridy = 2;
		contentPane.add(panel_regEvents, gbc_panel_regEvents);
		
		
		eventThisWeek = new EventsThisWeekGui();
		panel_eventThisWeek = new JPanel();
		panel_eventThisWeek.add(eventThisWeek.getMainPanel());
		GridBagConstraints gbc_panel_eventThisWeek = new GridBagConstraints();
		gbc_panel_eventThisWeek.insets = new Insets(5, 5, 5, 5);
		gbc_panel_eventThisWeek.fill = GridBagConstraints.BOTH;
		gbc_panel_eventThisWeek.gridx = 3;
		gbc_panel_eventThisWeek.gridy = 5;
		panel_eventThisWeek.setFocusable(false);
		panel_eventThisWeek.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.LOWERED),
				"Reminder! The events below are happening this week, be sure to register in time!", TitledBorder.CENTER, TitledBorder.TOP, fontTitle,
				Color.white));
		contentPane.add(panel_eventThisWeek, gbc_panel_eventThisWeek);
		
		
	}
	
	public void refreshMessageDisplay() {
		this.contentPane.remove(panel_messageDisplay);
		messageDisplay = null;
		panel_messageDisplay = null;
		messageDisplay = new MessageDisplayGUI(this);
		panel_messageDisplay = messageDisplay.getMainPanel();
		panel_messageDisplay.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.LOWERED),
				"Messages", TitledBorder.RIGHT, TitledBorder.TOP, fontTitle,
				Color.white));
		GridBagConstraints gbc_panel_messageDisplay = new GridBagConstraints();
		gbc_panel_messageDisplay.gridheight = 3;
		gbc_panel_messageDisplay.weighty = 1.0;
		gbc_panel_messageDisplay.weightx = 0.5;
		gbc_panel_messageDisplay.insets = new Insets(5, 5, 5, 5);
		gbc_panel_messageDisplay.fill = GridBagConstraints.BOTH;
		gbc_panel_messageDisplay.gridx = 1;
		gbc_panel_messageDisplay.gridy = 2;
		contentPane.add(panel_messageDisplay, gbc_panel_messageDisplay);
		contentPane.revalidate();
	}
	
	public void updateRequestedEvents() {
		panel_regEvents.remove(registeredEventsGUIPanel);

		registeredEventsGUIGrid.updateTable();
		registeredEventsGUIPanel = null;
		registeredEventsGUIPanel = registeredEventsGUIGrid.getMainPanel();
		panel_regEvents.add(registeredEventsGUIPanel);
		
		contentPane.revalidate();
	}

	public JPanel getMainPanel() {
		return contentPane;
	}
}
