package ba.etf.tim11.eCinema.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Group;


@Path("groups")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource 
{	
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
	@Consumes("application/x-www-form-urlencoded")
	public Group createNewGroup(MultivaluedMap<String, String> formParams) 
	{
		Group group = new Group();
		
		group.setName(formParams.getFirst("name"));
		group.setDescription(formParams.getFirst("description"));

		return groupDao.insert(group);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") int id) 
	{
		Group group = groupDao.find(id);
		groupDao.delete(group);
	}

} 


