package swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import hibernate.LoginManager;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(LoginFrame.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(271, 35, 56, 16);
		contentPane.add(lblAddress);
		
		textField = new JTextField();
		textField.setBounds(316, 32, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(271, 85, 56, 16);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(316, 82, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(316, 142, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(271, 145, 56, 16);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// LoginManager manager=new LoginManager();
				// if( manager.checkLogin(name, pass)){
				// slf4jLogger.info("info:"+"log in done!");//log4j palce
				// MainFrame mainFrame=new MainFrame();
				// mainFrame.setVisible(true);
				//
				// }
				// else{
				// slf4jLogger.info("info:"+"wrong username or pass!");
				// JOptionPane.showMessageDialog(null, "Login
				// failed!","Failed!!",
				// JOptionPane.ERROR_MESSAGE);
				// }

				// Call_Info myInfo=new
				// Call_Info("aba","big","000","9999","www");
				// CallManager manager2=new CallManager();
				// /manager2.Add(myInfo);
				String server = textField.getText();
				String name = textField_1.getText();
				String pass = textField_2.getText();
				
				
				if(server.equals("http://localhost:8080/Rest-hw12/api/FinalProject/login")){
					Client client=new Client();
					try {
						String flag=client.validation(name, pass);
					System.out.println(flag);
						if (flag.equals("true")) {
							dispose();
							OptionalLogin ud = new OptionalLogin();
							ud.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(contentPane, "Your username or password invalid");
						client.saverole(name);
						System.out.println("save rolemap sucsseful");
					} catch (UnsupportedOperationException | IOException l) {
						// TODO Auto-generated catch block
						l.printStackTrace();
					}}
					else
						JOptionPane.showMessageDialog(contentPane, "Your address server invalid");	

			}
		});
		btnLogin.setBounds(316, 219, 97, 25);
		contentPane.add(btnLogin);
		
		JButton btnShoecontact = new JButton("ShowContact");
		btnShoecontact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client client = new Client();
				// ArrayList <User> users=new ArrayList<User>();
				// users=(ArrayList<User>) client.getRequest();
				// System.out.println(users.get(0).getUsername());

				try {

					String contactshowall;
					contactshowall = client.getContacts();
					System.out.println(contactshowall);
					// String[] ary = contactshowall.split(",");
					// for(int i=0;i<ary.length;i++)
					// {
					// String[] ary2 = ary[i].split(":");
					// for(int j=1;j<ary2.length;j+=2)
					// System.out.println(ary2[j]+" "+"morede badi");
					// Object[][] data =new Object[numberrow][6];
					// }
					// public List JsonToPhoneList(String contactshowall){
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<List<Contact>>() {
					}.getType();
					List<Contact> list = gson.fromJson(contactshowall, type);
					// return list;
					// }
					System.out.println(list.size());
					Object[][] data = new Object[list.size()][6];
					for (int i = 0; i < list.size(); i++) {

						data[i][0] = list.get(i).getId();
						data[i][1] = list.get(i).getName();
						data[i][2] = list.get(i).getFamily();
						data[i][3] = list.get(i).getCellphone();
						data[i][4] = list.get(i).getHomephone();
						data[i][5] = list.get(i).getEmail();
					}
					String[] columns = new String[] { "id", "name", "family", "cellphone", "homephone", "email" };
					// table = new JTable();
					JTable table = new JTable(data, columns);

					// this.pack();
					table.setVisible(true);
					JScrollPane pane = new JScrollPane(table);
					pane.setSize(200, 200);
					pane.setLocation(43, 51);
					contentPane.add(pane);

				} catch (UnsupportedOperationException | IOException m) {
					// TODO Auto-generated catch block
					m.printStackTrace();
				}

			}
		});
		btnShoecontact.setBounds(318, 186, 97, 25);
		contentPane.add(btnShoecontact);
	}
}
