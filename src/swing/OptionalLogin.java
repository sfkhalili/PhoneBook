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

public class OptionalLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionalLogin frame = new OptionalLogin();
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
	public OptionalLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddcontact = new JButton("AddContact");
		btnAddcontact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				InsertConatctForm ud = new InsertConatctForm();
				ud.setVisible(true);
			}
		});
		btnAddcontact.setBounds(323, 49, 97, 25);
		contentPane.add(btnAddcontact);
		
		JButton btnShowcontact = new JButton("ShowContact");
		btnShowcontact.addActionListener(new ActionListener() {
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

				} catch (UnsupportedOperationException | IOException l) {
					// TODO Auto-generated catch block
					l.printStackTrace();
				}

			}
		});
		btnShowcontact.setBounds(323, 112, 97, 25);
		contentPane.add(btnShowcontact);
	}

}

