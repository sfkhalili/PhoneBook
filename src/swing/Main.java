package swing;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
//		User user=new User("elham8","elham",1);
//		hibernate.Insertuser(user);
//		Contact c=new Contact("elham","khalili",653412,9128645,"fatima.khalili2012.gmail.com");
//		hibernate.Insertcontact(c);
//		Date date=new Date();
//		Event event=new Event(date, "login kard", user);
////		hibernate.Insertevent(event);

		LoginFrame  lf= new LoginFrame();
		lf.setVisible(true);

		}
}