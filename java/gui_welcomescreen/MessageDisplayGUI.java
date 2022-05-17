package gui_welcomescreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.GUIMain;
import structural.handlers.MessageBoardManager;
import structural.handlers.PathsAccesser;
import structural.members.Member;

import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.util.List;
import javax.swing.JTextArea;

public class MessageDisplayGUI extends JFrame {

	private JPanel contentPane;
	private Member member;
	private JScrollPane scrollPane;
	private GridBagConstraints gbc_scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain guiMain = new GUIMain();
					WelcomeScreen welcomeScreen = new WelcomeScreen(guiMain);
					MessageDisplayGUI frame = new MessageDisplayGUI(welcomeScreen);
					frame.setResizable(false);
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
	public MessageDisplayGUI(WelcomeScreen welcomeScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0};
		gbl_contentPane.columnWeights = new double[]{1.0};
		contentPane.setLayout(gbl_contentPane);
		loadMessages();
		contentPane.add(scrollPane, gbc_scrollPane);
	
	}
	
	private void loadMessages() {
		scrollPane = null;
		MessageBoardManager manager = new MessageBoardManager(new PathsAccesser(), member);
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		textArea.setEditable(false);
		List<String[]> messages = manager.readMessages();
		String messagesDisplayed = "";
		for(int index = 0; index<messages.size(); index++) {
			String[] row = messages.get(index);
			String message = row[0];
			String messageWriterName = row[1];
			messagesDisplayed += messageWriterName + " has written: \n " + message + "\n";
		}
		textArea.setText(messagesDisplayed);
		scrollPane = new JScrollPane(textArea);
		gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scrollPane.getVerticalScrollBar().setValue(0);
			   	}
		});
			
		}

	public JPanel getMainPanel() {
		return contentPane;
	}

}
