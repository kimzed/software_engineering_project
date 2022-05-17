package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import structural.handlers.PathsAccesser;
import structural.members.Member;
import structural.members.MemberList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MemberAdder extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private MemberList memberList = new MemberList(new PathsAccesser()) ;
	private JTextField passwordtextField;
	private JTextField nametextField;
	private JTextField birthdatetextField;
	private JTextField emailtextField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberAdder frame = new MemberAdder();
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
	public MemberAdder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		
		lblNewLabel_1 = new JLabel("Name:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.weightx = 1.0;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		nametextField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.weightx = 1.0;
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		contentPane.add(nametextField, gbc_textField_1);
		nametextField.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Birth date:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.weightx = 1.0;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		birthdatetextField = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.weightx = 1.0;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 1;
		contentPane.add(birthdatetextField, gbc_textField_2);
		birthdatetextField.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Email:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.weightx = 1.0;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 2;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 4;
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_11.gridx = 5;
		gbc_lblNewLabel_11.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel_11);
		
		emailtextField = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.weightx = 1.0;
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 2;
		contentPane.add(emailtextField, gbc_textField_4);
		emailtextField.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.weightx = 1.0;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		passwordtextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weightx = 1.0;
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		contentPane.add(passwordtextField, gbc_textField);
		passwordtextField.setColumns(10);
		
		btnNewButton = new JButton("Add new member");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nametextField.getText();
				String birthdate = birthdatetextField.getText();
				String password = passwordtextField.getText();
				String email = emailtextField.getText();
				boolean nameIsPresent = !nametextField.getText().equals("");
				boolean birthdateIsPresent = !birthdatetextField.getText().equals("");
				boolean passwordIsPresent = !passwordtextField.getText().equals("");
				boolean emailIsPresent = !emailtextField.getText().equals("");
				
				if(nameIsPresent && birthdateIsPresent && passwordIsPresent && emailIsPresent) {
					Member member = new Member(name,birthdate,password,email);
					try {
						addMember(member);
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}
				else {
					JOptionPane.showMessageDialog(contentPane,
						    "Please leave no fields empty");					
				}
			
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_1.weightx = 1.0;
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 4;
		contentPane.add(btnNewButton, gbc_btnNewButton_1);		
	}
	private void addMember(Member member) throws Exception {
		memberList.readMembers();
		String text;
		try {
			memberList.addMember(member);
			memberList.saveMembers();
			int i_Member = memberList.members.size()-1;
			String member_name = memberList.members.get(i_Member).getName();
			text = "added: "+member_name;
		}			
		catch(IllegalArgumentException e) {
			text = "Member already exists";
		}
				
		
		lblNewLabel.setText(text);
		
		
	}
	public JPanel getMainPanel() {
		return contentPane;
	}

}
