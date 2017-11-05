
package swing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Contact")
@Entity
@Table(name="contact")
public  class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	@Column(name="name" )	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name =name;
	}

	@Column(name="family")
	private String family;
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}

	@Column(name="homephone")
	private long homephone;
	public long getHomephone() {
		return homephone;
	}
	public void setHomephone(long homephone) {
		this.homephone = homephone;
	}

	@Column(name="cellphone")
	private long cellphone;
	public long getCellphone() {
		return cellphone;
	}
	public void setCellphone(long cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name="email")
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Contact(String name,String family,long homephone,long cellphone,String email){

		this.name=name;
		this.family=family;
		this.homephone=homephone;
		this.cellphone=cellphone;
		this.email=email;
	}
	public Contact(){

	}
}
