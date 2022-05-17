package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import structural.handlers.LoginValidator;
import structural.handlers.PathsAccesser;
import structural.members.Member;
import structural.members.MemberList;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ChangePasswordGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtcurrentPassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtRepeatNewPassword;
	private Member member;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Member member) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordGUI frame = new ChangePasswordGUI(member);
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
	public ChangePasswordGUI(Member member) {
		this.member = member;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblCurrentPassword = new JLabel("Current password:");
		GridBagConstraints gbc_lblCurrentPassword = new GridBagConstraints();
		gbc_lblCurrentPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentPassword.anchor = GridBagConstraints.EAST;
		gbc_lblCurrentPassword.gridx = 0;
		gbc_lblCurrentPassword.gridy = 2;
		contentPane.add(lblCurrentPassword, gbc_lblCurrentPassword);
		
		txtcurrentPassword = new JPasswordField();
		GridBagConstraints gbc_txtcurrentPassword = new GridBagConstraints();
		gbc_txtcurrentPassword.anchor = GridBagConstraints.WEST;
		gbc_txtcurrentPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtcurrentPassword.gridx = 1;
		gbc_txtcurrentPassword.gridy = 2;
		contentPane.add(txtcurrentPassword, gbc_txtcurrentPassword);
		txtcurrentPassword.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New password:");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
		gbc_lblNewPassword.gridx = 0;
		gbc_lblNewPassword.gridy = 3;
		contentPane.add(lblNewPassword, gbc_lblNewPassword);
		
		txtNewPassword = new JPasswordField();
		GridBagConstraints gbc_txtNewPassword = new GridBagConstraints();
		gbc_txtNewPassword.anchor = GridBagConstraints.WEST;
		gbc_txtNewPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewPassword.gridx = 1;
		gbc_txtNewPassword.gridy = 3;
		contentPane.add(txtNewPassword, gbc_txtNewPassword);
		txtNewPassword.setColumns(10);
		
		JLabel lblRepeatNewPassword = new JLabel("Repeat new password:");
		GridBagConstraints gbc_lblRepeatNewPassword = new GridBagConstraints();
		gbc_lblRepeatNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepeatNewPassword.anchor = GridBagConstraints.EAST;
		gbc_lblRepeatNewPassword.gridx = 0;
		gbc_lblRepeatNewPassword.gridy = 4;
		contentPane.add(lblRepeatNewPassword, gbc_lblRepeatNewPassword);
		
		txtRepeatNewPassword = new JPasswordField();
		GridBagConstraints gbc_txtRepeatNewPassword = new GridBagConstraints();
		gbc_txtRepeatNewPassword.anchor = GridBagConstraints.WEST;
		gbc_txtRepeatNewPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtRepeatNewPassword.gridx = 1;
		gbc_txtRepeatNewPassword.gridy = 4;
		contentPane.add(txtRepeatNewPassword, gbc_txtRepeatNewPassword);
		txtRepeatNewPassword.setColumns(10);
		
		JButton btnChangePassword = new JButton("Change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isvalidChangePasswordAttempt()) {
					changePassword();
				}
				else {
									}
				
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 7;
		contentPane.add(btnChangePassword, gbc_btnNewButton_1);
	}
	
	private boolean isvalidChangePasswordAttempt() {
		if(!member.passwordIsCorrect(txtcurrentPassword.getText())) {
			JOptionPane.showMessageDialog(contentPane,
				    "Password incorrect",
				    " ",
				    JOptionPane.ERROR_MESSAGE);

			return false;
		}
		if(!txtRepeatNewPassword.getText().equals(txtNewPassword.getText()) ) {
			JOptionPane.showMessageDialog(contentPane,
				    "The new passwords are not the same",
				    " ",
				    JOptionPane.ERROR_MESSAGE);

			return false;
		}		
		JOptionPane.showMessageDialog(contentPane,
			    "You have succesfully changed your password");
		return true;		
	}
	private void changePassword() {
		PathsAccesser pathAccesser = new PathsAccesser();
		MemberList memberList = new MemberList(pathAccesser);
		memberList.readMembers();
		memberList.removeMember(member);		
		member.setPassword(txtRepeatNewPassword.getText());
		memberList.addMember(member);
		memberList.saveMembers();
		
		}
	
	
	public JPanel getMainPanel() {
		return contentPane;
	}

}
