package swing;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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

import com.mchange.v2.codegen.bean.Property;

@Path("/FinalProject")
public class RestServer {
	static{
	BasicConfigurator.configure();}
	static  Logger log = Logger.getLogger(RestServer.class);

	
	static HashMap<String, User> map = new HashMap<String, User>();

	// @GET
	// @Produces("application/xlm")
	// public String getData(@Context HttpServletRequest request){
	// String ip = request.getRemoteAddr();
	// System.out.println("Client IP = " + ip);
	// return ip;
	// }

	// login
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
		return Response.status(200).entity(flag).build();
	}

	// insert the contact
	@Path("/addcontact")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateContact(Contact c, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String ip = request.getRemoteAddr();
		User u = map.get(ip);
		if (u.getRole() == 4|| u.getRole() == 1 || u.getRole() == 2 || u.getRole() == 3) {// no
																							// guest

			hibernate.Insertcontact(c);
			log.info("save successful");
			return Response.status(200).entity("insert successfuly").build();
		} else {
			return Response.status(200).entity("you not access").build();
		}
	}

	// show all contacts
	@Path("/showcontact")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> selectContact(@Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		String ip = request.getRemoteAddr();
		User u = map.get(ip);
		if (u != null) {
			if (u.getRole() == 4 || u.getRole() == 1 || u.getRole() == 2 || u.getRole() == 3) {
				List<Contact> contacts = hibernate.selectContacts();
				log.debug("get successful");
				return contacts;
			} else {
				return null;
			}
		} else {
			List<Contact> contacts = hibernate.selectContacts();
			log.info("get successful");
			return contacts;
		}

	}

	// update by id
	@Path("/updatecontact/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContact(Contact c, @PathParam("id") String id, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String result;
		String ip = request.getRemoteAddr();
		User u = map.get(ip);
		if (u.getRole() == 4 || u.getRole() == 1 || u.getRole() == 2) {// managers
			if (hibernate.UpdateContact(c, Integer.parseInt(id))) {

				result = "The contact with id " + id + " update successfully.";
				System.out.println(result);
			} else {
				result = "The contact with id " + id + " which you want to update does'nt exist.";

			}
			return Response.status(200).entity(result).build();
		} else {
			return Response.status(200).entity("you not access").build();
		}

	}

	// Delete by id contact
	@Path("/deletecontact/{id}")
	@DELETE
	public Response DeleteContact(@PathParam("id") String id, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String result;

		String ip = request.getRemoteAddr();
		User u = map.get(ip);
		if (u.getRole() == 4 || u.getRole() == 1 || u.getRole() == 2) {// managers
			if (hibernate.DeleteContact(Integer.parseInt(id))) {

				result = "The contact with id " + id + " deleted successfully.";
				System.out.println(result);
			} else {
				result = "The contact with id " + id + " which you want to delete does'nt exist.";
				System.out.println(result);
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

	// insert the user
	@Path("/user")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Createuser(User u, @Context HttpServletRequest request) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {

		String ip = request.getRemoteAddr();
		u = map.get(ip);
		if (u.getRole() == 4 || u.getRole() == 1) {// just role 1

			hibernate.Insertuser(u);
			log.info("save successful");
			return Response.status(200).entity("insert successfuly customer").build();
		} else {
			return Response.status(200).entity("you not access").build();
		}

	}

	// update by id
	@Path("/updateuser/{username}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateuser(User c, @PathParam("username") String name, @Context HttpServletRequest request)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String result = "";
		String ip = request.getRemoteAddr();
		User u = map.get(ip);
		if (u.getRole() == 4 || u.getRole() == 1) {// managertotal
			if (hibernate.Updateuser(c, name)) {

				result = "The person with username " + name + " update successfully.";
				System.out.println(result);
			} else {
				result = "The person with username " + name + " which you want to update does'nt exist.";
				System.out.println(result);
			}
			return Response.status(200).entity(result).build();
		} else {
			return Response.status(200).entity("you not access").build();
		}
	}

}
