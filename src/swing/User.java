package swing;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public  class User {
	
	@Id
	@Column(name="username" ,updatable = false, nullable = false)	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password")
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="role")
	private int role;

	
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public User(String username,String password, int role){
		
		this.username=username;
		this.password=password;
		this.role=role;	
	}
	public User(){
		
	}
}
