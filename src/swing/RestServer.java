package swing;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/FinalProject")
public class RestServer {

	// insert the contact
	@Path("/addcontact")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateContact(Contact c)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		hibernate.Insertcontact(c);
		return Response.status(200).entity("insert successful contact with" ).build();
	}

	 //show all contacts
	@Path("/showcontact")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> selectContact()throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		List<Contact> tickets = hibernate.selectContacts();
		return tickets;
	}

	// update by id
	@Path("/updatecontact/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContact(Contact c, @PathParam("id") String id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String result;
		if (hibernate.UpdateContact(c, Integer.parseInt(id))) {

			result = "The contact with id " + id + " update successfully.";
			System.out.println(result);
		} else {
			result = "The contact with id " + id + " which you want to update does'nt exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();
	}

	// Delete by id contact
	@Path("/deletecontact/{id}")
	@DELETE
	public Response DeleteContact(@PathParam("id") String id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String result;
		if (hibernate.DeleteContact(Integer.parseInt(id))) {

			result = "The contact with id " + id + " deleted successfully.";
			System.out.println(result);
		} else {
			result = "The contact with id " + id + " which you want to delete does'nt exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();
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
	public Response Createuser(User u)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {
		
		hibernate.Insertuser(u);
		return Response.status(200).entity("insert successful customer" ).build();
	}
	
	// update by id
	@Path("/updateuser/{username}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateuser(User c, @PathParam("username") String name)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String result="";
		if (hibernate.Updateuser(c, name)) {

			result = "The person with username " + name + " update successfully.";
			System.out.println(result);
		} else {
			result = "The person with username " + name + " which you want to update does'nt exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();
	}
	
	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveperson(User u)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String result;
		int role=hibernate.saveuser(u.getUsername());
		result="the username have a role by number of"+role;
		return Response.status(200).entity(result).build();
	}
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validation(User u)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, NoSuchAlgorithmException {
		
		boolean flag=hibernate.validation(u);
		return Response.status(200).entity(flag ).build();
	}
}
