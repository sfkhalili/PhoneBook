package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertConatctForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertConatctForm frame = new InsertConatctForm();
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
	public InsertConatctForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setBounds(12, 25, 56, 16);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(80, 22, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblFamily = new JLabel("family");
		lblFamily.setBounds(12, 71, 56, 16);
		contentPane.add(lblFamily);

		textField_1 = new JTextField();
		textField_1.setBounds(80, 68, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("homephone");
		lblNewLabel_1.setBounds(12, 109, 56, 16);
		contentPane.add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setBounds(80, 103, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("cellphone");
		lblNewLabel_2.setBounds(12, 161, 56, 16);
		contentPane.add(lblNewLabel_2);

		textField_3 = new JTextField();
		textField_3.setBounds(80, 158, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(12, 206, 56, 16);
		contentPane.add(lblEmail);

		textField_4 = new JTextField();
		textField_4.setBounds(80, 203, 116, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JButton btnAddcontact = new JButton("ADDCONTACT");
		btnAddcontact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String family = textField_1.getText();
				String homephone = textField_2.getText();
				String cellphone = textField_3.getText();
				String email = textField_4.getText();
				Client client = new Client();
				client.saveContact(name, family, Long.parseLong(homephone), Long.parseLong(cellphone), email);
			
			}
		});
		btnAddcontact.setBounds(323, 112, 97, 25);
		contentPane.add(btnAddcontact);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				OptionalLogin ud = new OptionalLogin();
				ud.setVisible(true);
			}
		});
		btnBack.setBounds(323, 152, 97, 25);
		contentPane.add(btnBack);
	}
}
