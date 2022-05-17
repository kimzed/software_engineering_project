package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import gui_welcomescreen.WelcomeScreen;
import structural.members.Member;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * public GUI method which handles the main structure of the program uses
 * different GUI classes to obtain information contains 8 private parameters and
 * 7 methods
 * 
 * @author gianp
 *
 */
public class GUIMain {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;
	private MemberListGUI memberListGUI;
	private EventScheduleGUI eventScheduleGUI;
	private ChangePasswordGUI changePasswordGUI;
	private Member member;
	private WelcomeScreen welcomeScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatLaf.registerCustomDefaultsSource("com.GUIMain.themes");
		try {
			FlatDarkLaf.setup();
		} catch (Exception e) {
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain window = new GUIMain();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUIMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);

		LoginScreen loginScreen = initializeLoginScreen();
		loginScreen.setVisible(true);
		loginScreen.setResizable(false);
		loginScreen.setAlwaysOnTop(true);
		loginScreen.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	}

	/**
	 * initialize login screen on startup
	 * 
	 * @return
	 */
	private LoginScreen initializeLoginScreen() {
		LoginScreen loginScreen = new LoginScreen(this);
		return loginScreen;
	}

	/**
	 * updates tabs when tab is clicked
	 */
	private void updateAllTabs() {
		welcomeScreen.updateRequestedEvents();
		memberListGUI.updateTable();
		if (eventScheduleGUI != null) {
			eventScheduleGUI.updatePanel();
		}
	}

	/**
	 * shows view of a member in the application uses eventScheduleGUI to obtain the
	 * registered events of a member also uses memberListGUI to obtain and show a
	 * list of current members
	 * 
	 * @param member
	 * @throws IOException
	 * @throws ParseException
	 */
	public void showMemberGUI(Member member){
		this.member = member;
		welcomeScreen = new WelcomeScreen(this);
		JPanel welcomeScreenPanel = welcomeScreen.getMainPanel();
		tabbedPane.addTab("Home", null, welcomeScreenPanel, null);

		eventScheduleGUI = new EventScheduleGUI(member);
		JPanel eventSchedulePanel = eventScheduleGUI.getMainPanel();
		tabbedPane.addTab("Event schedule", null, eventSchedulePanel, null);

		memberListGUI = null;
		try {
			memberListGUI = new MemberListGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JPanel MemberListGUIPanel = memberListGUI.getMainPanel();
		tabbedPane.addTab("Member List", null, MemberListGUIPanel, null);

		EventRequestGUI eventRequestGUI = new EventRequestGUI(member);
		JPanel eventRequestGUIPanel = eventRequestGUI.getMainPanel();
		tabbedPane.addTab("Request event", null, eventRequestGUIPanel, null);

		changePasswordGUI = new ChangePasswordGUI(member);
		JPanel changePasswordGUIPanel = changePasswordGUI.getMainPanel();
		tabbedPane.addTab("Change password", null, changePasswordGUIPanel, null);
		
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateAllTabs();
			}
		});
	}

	/**
	 * shows view of a boardmember in the application uses eventScheduleGUI to
	 * obtain the registered events of a member uses memberListGUI to obtain and
	 * show a list of current members uses memberAdder to manually add members to
	 * the memberlist in the GUI
	 * 
	 * @param member
	 * @throws IOException
	 * @throws ParseException
	 */
	public void showBoardmemberGUI(Member member) {
		this.member = member;
		welcomeScreen = new WelcomeScreen(this);
		JPanel welcomeScreenPanel = welcomeScreen.getMainPanel();
		tabbedPane.addTab("Home", null, welcomeScreenPanel, null);

		eventScheduleGUI = new EventScheduleGUI(member);
		JPanel eventSchedulePanel = eventScheduleGUI.getMainPanel();
		tabbedPane.addTab("Event schedule", null, eventSchedulePanel, null);

		GenerateEventGUI scheduleEventGUI = new GenerateEventGUI();
		JPanel scheduleEventGUIPanel = scheduleEventGUI.getMainPanel();
		tabbedPane.addTab("Generate event", null, scheduleEventGUIPanel, null);

		memberListGUI = null;
		try {
			memberListGUI = new MemberListGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JPanel MemberListGUIPanel = memberListGUI.getMainPanel();
		tabbedPane.addTab("Member List", null, MemberListGUIPanel, null);

		MemberAdder memberAdder = new MemberAdder();
		JPanel memberAdderPanel = memberAdder.getMainPanel();
		tabbedPane.addTab("Adding member", null, memberAdderPanel, null);

		changePasswordGUI = new ChangePasswordGUI(member);
		JPanel changePasswordGUIPanel = changePasswordGUI.getMainPanel();
		tabbedPane.addTab("Change password", null, changePasswordGUIPanel, null);
		
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateAllTabs();
			}
		});
	}

	public void setLoggedMember(Member member) {
		this.member = member;
	}

	public Member getLoggedMember() {
		return member;
	}
}
