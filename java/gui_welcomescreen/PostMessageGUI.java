package gui_welcomescreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import structural.handlers.MessageBoardManager;
import structural.handlers.PathsAccesser;
import structural.members.Member;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PostMessageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterMessage;
	private JButton btnNewButton;
	private static Member member;
	private static WelcomeScreen welcomeScreen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			

			public void run() {
				try {
					PostMessageGUI frame = new PostMessageGUI(member, welcomeScreen);
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
	public PostMessageGUI(Member member, WelcomeScreen welcomeScreen) {
		this.welcomeScreen = welcomeScreen;
		this.member = member;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		

		
		txtEnterMessage = new JTextField();
		txtEnterMessage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				clearText();
			}
			
			public void clearText() {
				txtEnterMessage.setText("");
			}
		});
		txtEnterMessage.setText("Enter message ");
		contentPane.add(txtEnterMessage, BorderLayout.SOUTH);
		txtEnterMessage.setColumns(10);
		txtEnterMessage.setSize(300, 300);
		
		
		btnNewButton = new JButton("Add message");
		btnNewButton.setSize(300, 300);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleButtonClick();
				
			}

		});
		contentPane.add(btnNewButton, BorderLayout.WEST);
		this.btnNewButton = btnNewButton;
	}
	
	private void handleButtonClick() {
		
		String boardMessage = txtEnterMessage.getText();
		MessageBoardManager manager = new MessageBoardManager(new PathsAccesser(), member);
		manager.writeToMessageBoard(boardMessage);
		
		welcomeScreen.refreshMessageDisplay();
	}
	public JPanel getMainPanel() {
		return contentPane;
	}

}
