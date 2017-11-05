package swing;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.mapping.Map;
import org.junit.Test;

import com.mchange.v2.codegen.bean.Property;

@Path("/FinalProject")
public class RestServer {

	static {
		BasicConfigurator.configure();
	}
	static Logger log = Logger.getLogger(RestServer.class);
	static HashMap<String, User> map = new HashMap<String, User>();
	
	
	/************************************************************************** contacts ***************************************************************/
	

	/********************************************* login ***********************************************/
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validation(User u, @Context HttpServletRequest request) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {

		String ip = request.getRemoteAddr();
		int role = hibernate.getRole(u.getUsername());
		u.setRole(role);
		map.put(ip, u);

		boolean flag = hibernate.validation(u);

		Date date = new Date();
		Event event = new Event(date, u.getUsername() + "login successful!!", u);
		hibernate.Insertevent(event);

		if (flag) {
			return Response.status(200).entity("welcome").build();
		} else {
			return Response.status(200).entity("no welcome").build();
		}
	}

	/********************************************* Insertcontact ***********************************************/
	
	@Path("/addcontact")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateContact(Contact c, @Context HttpServletRequest request) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {

		String ip = request.getRemoteAddr();
		User u = map.get(ip);

		Date date = new Date();
		Event event = new Event(date, u.getUsername() + "press add contact!!", u);
		hibernate.Insertevent(event);

		if (u.getRole() == 1 || u.getRole() == 2 || u.getRole() == 3) {// no
																		// guest

			hibernate.Insertcontact(c);
			log.info("save successful");
			return Response.status(200).entity("insert successfuly").build();
		} else {
			return Response.status(200).entity("you not access").build();
		}
	}

	/********************************************* showcontact ***********************************************/
	
	@Path("/showcontact")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> selectContact(@Context HttpServletRequest request) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {

		String ip = request.getRemoteAddr();
		User u = map.get(ip);
		if (u != null) {

			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "press contact show!!", u);
			hibernate.Insertevent(event);

			if (u.getRole() == 1 || u.getRole() == 2 || u.getRole() == 3) {
				List<Contact> contacts = hibernate.selectContacts();
				log.debug("get successful");
				return contacts;
			} else {
				return null;
			}
		} else {

			Date date = new Date();
			u = new User();
			u.setUsername("guest");
			u.setRole(4);
			u.setPassword("");
			Event event = new Event(date, "guest press contact show!!", u);
			hibernate.Insertevent(event);

			List<Contact> contacts = hibernate.selectContacts();
			log.info("get successful for guest");
			return contacts;
		}

	}

	/********************************************* updatecontact ***********************************************/
	@Path("/updatecontact/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContact(Contact c, @PathParam("id") String id, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			NoSuchAlgorithmException {
		String result;
		String ip = request.getRemoteAddr();
		User u = map.get(ip);

		Date date = new Date();
		Event event = new Event(date, u.getUsername() + "press update contact!!", u);
		hibernate.Insertevent(event);

		if (u.getRole() == 1 || u.getRole() == 2) {// managers
			if (hibernate.UpdateContact(c, Integer.parseInt(id))) {

				result = "The contact with id " + id + " update successfully.";
				log.info("update successfuly");
			} else {
				result = "The contact with id " + id + " which you want to update does'nt exist.";
				log.info("update does'nt exis");
			}
			return Response.status(200).entity(result).build();
		} else {
			return Response.status(200).entity("you not access").build();

		}

	}

	/********************************************* deletecontact ***********************************************/
	@Path("/deletecontact/{id}")
	@DELETE
	public Response DeleteContact(@PathParam("id") String id, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			NoSuchAlgorithmException {
		String result;

		String ip = request.getRemoteAddr();
		User u = map.get(ip);

		Date date = new Date();
		Event event = new Event(date, u.getUsername() + "press delete contact!!", u);
		hibernate.Insertevent(event);

		if (u.getRole() == 1 || u.getRole() == 2) {// managers
			if (hibernate.DeleteContact(Integer.parseInt(id))) {

				result = "The contact with id " + id + " deleted successfully.";
				log.debug(result);
			} else {
				result = "The contact with id " + id + " which you want to delete does'nt exist.";
				log.debug(result);
			}
			return Response.status(200).entity(result).build();
		} else {
			return Response.status(200).entity("you not access").build();
		}
	}

	// select by id
	@Path("/showid/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Contact showContactbyid(@PathParam("id") String id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		return hibernate.selectContact(Integer.parseInt(id));

	}

	/************************************************************************** User ***************************************************************/

	/********************************************* Insertuser ***********************************************/
	@Path("/user")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Createuser(User u, @Context HttpServletRequest request) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {

	

		hibernate.Insertuser(u);
		log.info("save successful");
		Date date = new Date();
		//u.setRole(3);
		Event event = new Event(date, "user press register!!", u);
		hibernate.Insertevent(event);
		return Response.status(200).entity("insert successfuly customer").build();

	}

	/********************************************* updateuser ***********************************************/
	
	@Path("/updateuser/{username}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateinfouser(User c, @PathParam("username") String name, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			NoSuchAlgorithmException {

		String result = "";
		String ip = request.getRemoteAddr();
		User u = map.get(ip);

		if (u != null) {

			if (u.getRole() == 1) {// managertotal

				Date date = new Date();
				Event event = new Event(date, u.getUsername() + "update users!!", u);
				hibernate.Insertevent(event);

				if (hibernate.Updateinfouser(c, name)) {

					result = "The person with username " + name + " update info successfully.";
					log.debug(result);
				} else {
					result = "The person with username " + name + " which you want to update does'nt exist.";
					log.debug(result);
				}
				return Response.status(200).entity(result).build();
			} else {

				Date date = new Date();
				Event event = new Event(date, u.getUsername() + "you not access!!", u);
				hibernate.Insertevent(event);

				return Response.status(200).entity("you not access").build();
			}
		} else
			return Response.status(200).entity("you dont login!!").build();
	}

	/********************************************* changeroleuser ***********************************************/
	
	@Path("/changerole/{username}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateuser(User c, @PathParam("username") String name, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			NoSuchAlgorithmException {

		String result = "";
		String ip = request.getRemoteAddr();
		User u = map.get(ip);

		if (u.getRole() == 1) {// managertotal

			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "update role user!", u);
			hibernate.Insertevent(event);

			if (hibernate.Updateuser(c, name)) {

				result = "The person with username " + name + " update role successfully.";
				log.debug(result);
			} else {
				result = "The person with username " + name + " which you want to update does'nt exist.";
				log.debug(result);
			}
			return Response.status(200).entity(result).build();
		} else {

			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "not access!", u);
			hibernate.Insertevent(event);

			return Response.status(200).entity("you not access").build();
		}
	}

	/********************************************* search ***********************************************/
	
	@Path("/search/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> searchcontact(@PathParam("username") String name, @Context HttpServletRequest request)
			throws NoSuchAlgorithmException {

		String ip = request.getRemoteAddr();
		User u = map.get(ip);

		if (u.getRole() == 1 || u.getRole() == 2) {

			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "press search contact!!", u);
			hibernate.Insertevent(event);
			List<Contact> contacts = hibernate.searchcontact(name);

			log.debug("get events successful");
			return contacts;
		} else {
			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "you not access!!", u);
			hibernate.Insertevent(event);
			return null;
		}

	}

	/********************************************* deleteuser ***********************************************/
	
	@Path("/deleteuser/{username}")
	@DELETE
	public Response DeleteUser(@PathParam("username") String username, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			NoSuchAlgorithmException {
		String result;

		String ip = request.getRemoteAddr();
		User u = map.get(ip);
		if ( u.getRole() == 1) {// managers

			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "press delete contact!!", u);
			hibernate.Insertevent(event);

			if (hibernate.DeleteUser(username)) {

				result = "The contact with id " + username + " deleted successfully.";
				System.out.println(result);
			} else {
				result = "The contact with id " + username + " which you want to delete does'nt exist.";
				System.out.println(result);
			}
			return Response.status(200).entity(result).build();
		} else {
			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "you not access!!", u);
			hibernate.Insertevent(event);

			return Response.status(200).entity("you not access").build();
		}
	}

	/********************************************* showevents ***********************************************/
	
	@Path("/showevent")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> selectevent(@Context HttpServletRequest request) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {

		String ip = request.getRemoteAddr();
		User u = map.get(ip);

		if (u.getRole() == 1 || u.getRole() == 2) {

			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "press show event!!", u);
			hibernate.Insertevent(event);

			List<Event> events = hibernate.selectEvent();
			log.debug("get events successful");
			return events;
		} else {
			Date date = new Date();
			Event event = new Event(date, u.getUsername() + "you not access!!", u);
			hibernate.Insertevent(event);
			return null;
		}

	}
}
