package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import structural.handlers.LoginValidator;
import structural.handlers.PathsAccesser;
import structural.members.Member;
import structural.members.MemberList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtExamplemailcom;
	private JPasswordField txtPassword;
	private JLabel lblCorrectLogin;
	private JLabel lblWrongLogin;
	private JLabel lblCorrectLoginMember;
	private JLabel lblCorrectLoginAdmin;
	private JButton btnLogin;
	private GUIMain guimain;
	public boolean succesLogin = false;
	private JLabel lblIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain guimain = new GUIMain();
					LoginScreen frame = new LoginScreen(guimain);
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
	public LoginScreen(GUIMain guiMain) {
		this.guimain = guiMain;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 451, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);

		JLabel lblEmailAdress = new JLabel("Email Adress");
		GridBagConstraints gbc_lblEmailAdress = new GridBagConstraints();
		gbc_lblEmailAdress.weightx = 1.0;
		gbc_lblEmailAdress.anchor = GridBagConstraints.NORTH;
		gbc_lblEmailAdress.fill = GridBagConstraints.BOTH;
		gbc_lblEmailAdress.insets = new Insets(5, 5, 5, 5);
		gbc_lblEmailAdress.gridx = 1;
		gbc_lblEmailAdress.gridy = 0;
		contentPane.add(lblEmailAdress, gbc_lblEmailAdress);

		txtExamplemailcom = new JTextField();
		txtExamplemailcom.setText("example@mail.com");
		GridBagConstraints gbc_txtExamplemailcom = new GridBagConstraints();
		gbc_txtExamplemailcom.anchor = GridBagConstraints.WEST;
		gbc_txtExamplemailcom.fill = GridBagConstraints.BOTH;
		gbc_txtExamplemailcom.insets = new Insets(5, 5, 5, 0);
		gbc_txtExamplemailcom.gridx = 2;
		gbc_txtExamplemailcom.gridy = 0;
		contentPane.add(txtExamplemailcom, gbc_txtExamplemailcom);
		txtExamplemailcom.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 1;
		contentPane.add(lblPassword, gbc_lblPassword);

		JPasswordField txtPassword = new JPasswordField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.weightx = 1.0;
		gbc_txtPassword.anchor = GridBagConstraints.WEST;
		gbc_txtPassword.fill = GridBagConstraints.BOTH;
		gbc_txtPassword.insets = new Insets(5, 5, 5, 0);
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 1;
		contentPane.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);

		lblWrongLogin = new JLabel("Incorrect email/password");
		lblWrongLogin.setVisible(false);
		lblWrongLogin.setForeground(Color.RED);
		GridBagConstraints gbc_lblWrongLogin = new GridBagConstraints();
		gbc_lblWrongLogin.weighty = 1.0;
		gbc_lblWrongLogin.weightx = 1.0;
		gbc_lblWrongLogin.fill = GridBagConstraints.BOTH;
		gbc_lblWrongLogin.insets = new Insets(5, 5, 0, 5);
		gbc_lblWrongLogin.gridx = 1;
		gbc_lblWrongLogin.gridy = 2;
		contentPane.add(lblWrongLogin, gbc_lblWrongLogin);

		btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.weightx = 1.0;
		gbc_btnLogin.insets = new Insets(5, 5, 0, 0);
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.fill = GridBagConstraints.BOTH;
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 2;
		contentPane.add(btnLogin, gbc_btnLogin);

		lblCorrectLoginMember = new JLabel("Correct login!");
		GridBagConstraints gbc_lblCorrectLogin_1 = new GridBagConstraints();
		gbc_lblCorrectLogin_1.weightx = 1.0;
		gbc_lblCorrectLogin_1.weighty = 1.0;
		gbc_lblCorrectLogin_1.fill = GridBagConstraints.BOTH;
		gbc_lblCorrectLogin_1.insets = new Insets(5, 5, 0, 5);
		gbc_lblCorrectLogin_1.gridx = 1;
		gbc_lblCorrectLogin_1.gridy = 2;
		lblCorrectLoginMember.setVisible(false);
		lblCorrectLoginMember.setForeground(Color.GREEN);
		contentPane.add(lblCorrectLoginMember, gbc_lblCorrectLogin_1);

		lblCorrectLoginAdmin = new JLabel("Admin Login!");
		GridBagConstraints gbc_lblCorrectLogin_2 = new GridBagConstraints();
		gbc_lblCorrectLogin_2.weightx = 1.0;
		gbc_lblCorrectLogin_2.weighty = 1.0;
		gbc_lblCorrectLogin_2.fill = GridBagConstraints.BOTH;
		gbc_lblCorrectLogin_2.insets = new Insets(5, 5, 0, 5);
		gbc_lblCorrectLogin_2.gridx = 1;
		gbc_lblCorrectLogin_2.gridy = 2;
		lblCorrectLoginAdmin.setVisible(false);
		lblCorrectLoginAdmin.setForeground(Color.GREEN);
		contentPane.add(lblCorrectLoginAdmin, gbc_lblCorrectLogin_2);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLogin();
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				if (succesLogin) {
					win.dispose();
				}

			}

			private void handleLogin() {
				String emailAdress = txtExamplemailcom.getText();
				char[] password = txtPassword.getPassword();
				LoginValidator loginValidator = new LoginValidator(new PathsAccesser());

				boolean isAdminCorrect = loginValidator.validateAdminLogin(emailAdress, password);
				boolean isBoardMember = loginValidator.validateLoginBoardMembers(emailAdress, password);
				boolean isMemberCorrect = loginValidator.validateLoginMembers(emailAdress, password);

				if (isAdminCorrect) {
					displayLoginAdminIsSuccesful();
					succesLogin = true;
				} else if (isBoardMember) {
					MemberList memberList = new MemberList(new PathsAccesser());
					memberList.readMembers();
					Member member = memberList.getMemberBasedOnEmail(emailAdress);
					displayLoginMemberIsSuccesful();
					guiMain.setLoggedMember(member);
					if (guiMain != null) {
						guiMain.showBoardmemberGUI(member);
					}

					succesLogin = true;
				} else if (isMemberCorrect) {
					MemberList memberList = new MemberList(new PathsAccesser());
					memberList.readMembers();
					Member member = memberList.getMemberBasedOnEmail(emailAdress);
					guiMain.setLoggedMember(member);
					displayLoginMemberIsSuccesful();
					guiMain.showMemberGUI(member);
					succesLogin = true;
				} else {
					displayLoginIsNotSuccesful();
					succesLogin = false;
				}
			}

			private void displayLoginAdminIsSuccesful() {
				lblWrongLogin.setVisible(false);
				lblCorrectLoginMember.setVisible(false);
				lblCorrectLoginAdmin.setVisible(true);

			}

			private void displayLoginMemberIsSuccesful() {
				lblWrongLogin.setVisible(false);
				lblCorrectLoginMember.setVisible(true);
				lblCorrectLoginAdmin.setVisible(false);

			}

			private void displayLoginIsNotSuccesful() {
				lblCorrectLoginAdmin.setVisible(false);
				lblCorrectLoginMember.setVisible(false);
				lblWrongLogin.setVisible(true);

			}

		});

		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});

		txtExamplemailcom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					txtPassword.requestFocusInWindow();
				}
			}
		});

		txtExamplemailcom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtExamplemailcom.setText("");
			}
		});
	}

}
