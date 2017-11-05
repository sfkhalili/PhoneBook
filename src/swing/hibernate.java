package swing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class hibernate {

	static Configuration cfg = new Configuration().configure("hibernate1.cfg.xml");

	static SessionFactory factory = cfg.buildSessionFactory();

	/********************************************* Insertevent ***********************************************/

	public static void Insertevent(Event e) {

		Session session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(e);
			System.out.println(e.getId());
			System.out.println("successfully saved");
			tx.commit();
		} catch (HibernateException e1) {
			if (tx != null)
				tx.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}

	}

	/********************************************* Insertcontact ***********************************************/

	public static void Insertcontact(Contact c1) {

		Session session = factory.openSession();
		Transaction tx = null;
		Integer Contact = null;

		try {

			tx = session.beginTransaction();
			session.save(c1);
			System.out.println(c1.getId());
			System.out.println("successfully saved");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/********************************************* selectContacts ***********************************************/

	public static List<Contact> selectContacts() {
		List<Contact> listCategories = null;
		Transaction tx = null;
		Session session = factory.openSession();
		// ArrayList<Contact> contacts= new ArrayList<Contact>();

		try {
			tx = session.beginTransaction();
			String hql = "from Contact";
			Query query = session.createQuery(hql);
			listCategories = query.list();

			for (Contact aCategory : listCategories) {
				System.out.println(aCategory.getName());
			}

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listCategories;

	}

	/********************************************* DeleteContact ***********************************************/

	public static boolean DeleteContact(int idcontact) {

		Transaction tx = null;
		Integer TicketID = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			Contact t = (Contact) session.get(Contact.class, idcontact);
			session.delete(t);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;

	}

	/********************************************* updateContact ***********************************************/

	public static boolean UpdateContact(Contact c, int id) {

		Transaction tx = null;
		Integer TicketID = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			Contact t = (Contact) session.get(Contact.class, id);
			if (t != null) {
				t.setName(c.getName());
				t.setCellphone(c.getCellphone());
				t.setEmail(c.getEmail());
				t.setFamily(c.getFamily());
				t.setHomephone(c.getHomephone());
				session.update(t);
				tx.commit();
				return true;
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;

	}

	public static Contact selectContact(int id) {

		Transaction tx = null;
		Integer TicketID = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			Contact t = new Contact();
			t = (Contact) session.get(Contact.class, id);
			tx.commit();
			return t;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	/********************************************* InsertUser ***********************************************/

	public static void Insertuser(User u) throws NoSuchAlgorithmException {

		Session session = factory.openSession();
		Transaction tx = null;
		Integer Contact = null;

		try {
			u.setPassword(hashpassword(u.getPassword()));
			u.setRole(3);
			tx = session.beginTransaction();
			session.save(u);
			System.out.println(u.getUsername());
			System.out.println("successfully saved");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/********************************************* updateuser ***********************************************/
	public static boolean Updateuser(User u, String username) {

		Transaction tx = null;
		// Integer TicketID = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			User t = (User) session.get(User.class, username);
			if (t != null) {
				t.setRole(u.getRole());
				session.update(t);
				tx.commit();
				System.out.println("role changed");
				return true;
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;

	}

	/********************************************* updateinfouser ***********************************************/

	public static boolean Updateinfouser(User u, String username) throws NoSuchAlgorithmException {

		Transaction tx = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			User t = (User) session.get(User.class, username);
			if (t != null) {
				t.setUsername(u.getUsername());
				u.setPassword(hashpassword(u.getPassword()));
				t.setRole(u.getRole());
				session.update(t);
				tx.commit();
				System.out.println("UPDATE USER	");
				return true;
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;

	}

	/********************************************* gotrole ***********************************************/
	public static int getRole(String username) {

		Transaction tx = null;
		Integer TicketID = null;
		Session session = factory.openSession();

		tx = session.beginTransaction();
		// String hql = "from User where username ='username'";
		String hql = "from User where username like :keyword";
		Query query = session.createQuery(hql);
		query.setParameter("keyword", "%" + username + "%");
		List<User> listUser = query.list();
		int role = listUser.get(0).getRole();
		return role;

	}

	/********************************************* validation ***********************************************/

	public static boolean validation(User u) throws NoSuchAlgorithmException {

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = new User();
			user = (User) session.get(User.class, u.getUsername());
			tx.commit();
			if (user != null & user.getUsername().equals(u.getUsername())
					& user.getPassword().equals(hashpassword(u.getPassword()))) {

				return true;
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	/********************************************* hashspassword ***********************************************/

	public static String hashpassword(String pass) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++)
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

		System.out.println("Digest(in hex format):: " + sb.toString());
		return sb.toString();

	}

	/********************************************* Search ***********************************************/
	public static List<Contact> searchcontact(String name) {

		List<Contact> listc = null;
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			String hql = "from Contact where name like :keyword";
			Query query = session.createQuery(hql);
			query.setParameter("keyword", "%" + name + "%");
			listc = query.list();

		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return listc;
	}

	/********************************************* DeleteUser ***********************************************/
	public static boolean DeleteUser(String username) {

		Transaction tx = null;
		Integer TicketID = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			User u = (User) session.get(User.class, username);
			session.delete(u);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;

	}

	/********************************************* selectEvent ***********************************************/

	public static List<Event> selectEvent() {
		List<Event> listCategories = null;
		Transaction tx = null;
		Session session = factory.openSession();

		try {
			tx = session.beginTransaction();
			String hql = "from Event";
			Query query = session.createQuery(hql);
			listCategories = query.list();

			for (Event aCategory : listCategories) {
				System.out.println(aCategory.getMessage());
			}

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listCategories;

	}

}
