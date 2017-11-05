package swing;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="event")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="event")
	private String message;
	
	@NotFound( action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) throws NoSuchAlgorithmException {
		this.user=new User();
		this.user.setUsername(user.getUsername()); 
		this.user.setRole(user.getRole());
		//this.user.setPassword(user.getPassword());
		this.user.setPassword(hibernate.hashpassword(user.getPassword()));
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Event(Date date, String message, User user) throws NoSuchAlgorithmException {

		this.date = date;
		this.message = message;
		setUser(user);}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
