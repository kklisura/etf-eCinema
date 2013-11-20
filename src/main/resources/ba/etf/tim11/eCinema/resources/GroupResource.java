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
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Group;


@Path("group")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {
	
	private DaoFactory daoFactory;
	private GroupDao groupDao;
	
	public GroupResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.groupDao = daoFactory.getGroupDao();
	}
	@GET
	public List<Group> getAllGroups() 
	{
		return groupDao.findAll();
	}
	
	@GET
	@Path("{id}")
	public Group getGroup(@PathParam("id") int id) 
	{
		return groupDao.find(id);
	}
	
	@POST
	public void createNewGroup() 
	{
		
	}
	
	@DELETE
	@Path("{id}")
	public void deleteGroup(@PathParam("id") int id) 
	{
		
	}

} 


