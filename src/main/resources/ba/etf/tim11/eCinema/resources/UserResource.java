package ba.etf.tim11.eCinema.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.User;



@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource 
{
	private DaoFactory daoFactory;
	private UserDao userDao;
	
	public UserResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.userDao = daoFactory.getUserDao();
	}
	@GET
	public List<User> getAllUsers() 
	{
		return userDao.findAll();
	}
	
	@GET
	@Path("{id}")
	public User getUser(@PathParam("id") int id) 
	{
		return userDao.find(id);
	}
	
	@POST
	public void createNewUser() 
	{
		
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") int id) 
	{
		
	}
} 
