package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import structural.handlers.PathsAccesser;
import structural.members.Member;
import structural.members.MemberList;

import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MemberListGUI extends JFrame {

	private JPanel contentPane;
	private MemberList memberList = new MemberList(new PathsAccesser());
	private JLabel lblAdditionInformation;
	private JLabel lblMemberlist;
	private JPanel jPanel1;
	private JTextArea jTextArea1;
	private JList<?> list1;
	private JLabel emaillabel;
	private JLabel DateOfBirthlabel;
	private JLabel email_text;
	private JLabel DateOfBirth_text;
	private JLabel namelabel;
	private JLabel Name_text;
	private ScrollPane scrollPane;
	private JTextField searchable;
	private JButton btnSearch;
	private DefaultListModel<String> listModel;
	private ArrayList<String> listDates;
	private ArrayList<String> listEmails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberListGUI frame = new MemberListGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public MemberListGUI() throws IOException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 213, 0, 1, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 1, 0, 0, 0, 0, 0, 0, 55 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		searchable = new JTextField("search for name or e-mail");

		GridBagConstraints gbc_searchable = new GridBagConstraints();
		gbc_searchable.gridwidth = 3;
		gbc_searchable.insets = new Insets(5, 5, 5, 5);
		gbc_searchable.fill = GridBagConstraints.BOTH;
		gbc_searchable.gridx = 0;
		gbc_searchable.gridy = 1;
		panel.add(searchable, gbc_searchable);
		searchable.setColumns(10);

		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.insets = new Insets(5, 5, 5, 5);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 1;

		lblMemberlist = new JLabel("Memberlist");
		GridBagConstraints gbc_lblMemberlist = new GridBagConstraints();
		gbc_lblMemberlist.gridwidth = 4;
		gbc_lblMemberlist.insets = new Insets(0, 0, 5, 5);
		gbc_lblMemberlist.gridx = 0;
		gbc_lblMemberlist.gridy = 2;
		panel.add(lblMemberlist, gbc_lblMemberlist);

		lblAdditionInformation = new JLabel("Show additional info on:");
		GridBagConstraints gbc_lblAdditionInformation = new GridBagConstraints();
		gbc_lblAdditionInformation.anchor = GridBagConstraints.WEST;
		gbc_lblAdditionInformation.insets = new Insets(0, 0, 5, 10);
		gbc_lblAdditionInformation.fill = GridBagConstraints.VERTICAL;
		gbc_lblAdditionInformation.gridx = 6;
		gbc_lblAdditionInformation.gridy = 2;
		panel.add(lblAdditionInformation, gbc_lblAdditionInformation);

		namelabel = new JLabel("Name:");
		GridBagConstraints gbc_namelabel = new GridBagConstraints();
		gbc_namelabel.insets = new Insets(0, 0, 5, 5);
		gbc_namelabel.gridx = 5;
		gbc_namelabel.gridy = 4;
		panel.add(namelabel, gbc_namelabel);

		Name_text = new JLabel("");
		GridBagConstraints gbc_Name_text = new GridBagConstraints();
		gbc_Name_text.insets = new Insets(0, 0, 5, 5);
		gbc_Name_text.gridx = 6;
		gbc_Name_text.gridy = 4;
		panel.add(Name_text, gbc_Name_text);

		DateOfBirthlabel = new JLabel("Date of birth");
		GridBagConstraints gbc_DateOfBirthlabel = new GridBagConstraints();
		gbc_DateOfBirthlabel.insets = new Insets(0, 0, 5, 5);
		gbc_DateOfBirthlabel.gridx = 5;
		gbc_DateOfBirthlabel.gridy = 5;
		panel.add(DateOfBirthlabel, gbc_DateOfBirthlabel);

		DateOfBirth_text = new JLabel("");
		GridBagConstraints gbc_DateOfBirth_text = new GridBagConstraints();
		gbc_DateOfBirth_text.insets = new Insets(0, 0, 5, 5);
		gbc_DateOfBirth_text.gridx = 6;
		gbc_DateOfBirth_text.gridy = 5;
		panel.add(DateOfBirth_text, gbc_DateOfBirth_text);

		emaillabel = new JLabel("Email:");
		GridBagConstraints gbc_emaillabel = new GridBagConstraints();
		gbc_emaillabel.insets = new Insets(0, 0, 5, 5);
		gbc_emaillabel.gridx = 5;
		gbc_emaillabel.gridy = 6;
		panel.add(emaillabel, gbc_emaillabel);

		email_text = new JLabel("");
		GridBagConstraints gbc_email_text = new GridBagConstraints();
		gbc_email_text.insets = new Insets(0, 0, 5, 5);
		gbc_email_text.gridx = 6;
		gbc_email_text.gridy = 6;
		panel.add(email_text, gbc_email_text);

		listDates = new ArrayList<String>();
		listEmails = new ArrayList<String>();
		listModel = new DefaultListModel<String>();
		JList<String> list1_1 = new JList<String>(listModel);

		memberList.readMembers();
		ArrayList<Member> members = new ArrayList(memberList.members);
		Collections.sort(members);
		for (int i = 0; i < (members.size()); i++) {
			listModel.addElement(members.get(i).getName());
			listDates.add(members.get(i).getDateOfBirth());
			listEmails.add(members.get(i).getEmail());
		}

		btnSearch = new JButton("Search");
		contentPane.requestFocusInWindow();

		list1_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int index_member = list1_1.getSelectedIndex();
				memberList.readMembers();
				if (index_member >= 0 && index_member < memberList.members.size()) {
					String currentMember = listModel.get(index_member);
					String currentDateOfBirth = listDates.get(index_member);
					String currentEmail = listEmails.get(index_member);
					email_text.setText(currentEmail);
					DateOfBirth_text.setText(currentDateOfBirth);
					Name_text.setText(currentMember);
				} else {
					email_text.setText("");
					DateOfBirth_text.setText("");
					Name_text.setText("");
				}
			}
		});

		searchable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				clearList();
				searchText();
			}
		});

		scrollPane = new ScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		scrollPane.setSize(200, 200);
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.anchor = GridBagConstraints.NORTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);

		lblAdditionInformation.setVisible(true);
		scrollPane.add(list1_1);

		searchable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchable.setText("");
			}
		});

	}

	private void searchText() {
		memberList.readMembers();
		ArrayList<Member> members = new ArrayList(memberList.members);
		Collections.sort(members);
		for (int i = 0; i < members.size(); i++) {
			String currentName = members.get(i).getName();
			String currentDateOfBirth = members.get(i).getDateOfBirth();
			String currentEmail = members.get(i).getEmail();
			String input = searchable.getText();
			if (currentName.toLowerCase().contains(input.toLowerCase())
					|| currentDateOfBirth.toLowerCase().contains(input.toLowerCase())
					|| currentEmail.toLowerCase().contains(input.toLowerCase())) {
				listModel.addElement(members.get(i).getName());
				listDates.add(members.get(i).getDateOfBirth());
				listEmails.add(members.get(i).getEmail());
			}
		}
	}

	private void clearList() {
		listModel.removeAllElements();
		listDates.clear();
		listEmails.clear();
	}

	public JPanel getMainPanel() {
		return contentPane;
	}
	public void updateTable() {
		clearList();
		memberList.readMembers();
		ArrayList<Member> members = new ArrayList(memberList.members);
		Collections.sort(members);
		for (int i = 0; i < members.size(); i++) {
			String currentName = members.get(i).getName();
			String currentDateOfBirth = members.get(i).getDateOfBirth();
			String currentEmail = members.get(i).getEmail();
			String input = "";
			if (currentName.toLowerCase().contains(input.toLowerCase())
					|| currentDateOfBirth.toLowerCase().contains(input.toLowerCase())
					|| currentEmail.toLowerCase().contains(input.toLowerCase())) {
				listModel.addElement(members.get(i).getName());
				listDates.add(members.get(i).getDateOfBirth());
				listEmails.add(members.get(i).getEmail());
			}
		}
		
	}
}
