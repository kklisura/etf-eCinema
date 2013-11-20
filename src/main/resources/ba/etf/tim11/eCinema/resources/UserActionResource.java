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
import ba.etf.tim11.eCinema.dao.UserActionDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.UserAction;


@Path("useractions")
@Produces(MediaType.APPLICATION_JSON)
public class UserActionResource
{
	private DaoFactory daoFactory;
	private UserActionDao userActionDao;
	
	
	public UserActionResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.userActionDao = daoFactory.getUserActionDao();
	}
	
	@GET
	public List<UserAction> getAllUserActions() 
	{
		return userActionDao.findAll();
	}
	
	@GET
	@Path("{id}")
	public UserAction getUserAction(@PathParam("id") int id) 
	{
		return userActionDao.find(id);
	}
	
	@POST
	public void createNewUserAction() 
	{
		
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUserAction(@PathParam("id") int id) 
	{
		
	}

} 