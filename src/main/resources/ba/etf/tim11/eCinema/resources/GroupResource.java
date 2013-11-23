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
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


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
		Group group = groupDao.find(id);
		
		if (group == null) {
			throw new ResourceNotFoundException("Group not found");
		}
		
		return group;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Group createNewGroup(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "name", "description")) {
			throw new BadRequestException("You are missing name and/or description field.");
		}
		
		Group group = new Group();
		
		group.setName(formParams.getFirst("name"));
		group.setDescription(formParams.getFirst("description"));

		groupDao.insert(group);
		
		return group;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	public Response updateGroup(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Group group = groupDao.find(id);
		if (group == null) {
			throw new ResourceNotFoundException("Group not found.");
		}
		
		if (formParams.getFirst("name") != null)
			group.setName(formParams.getFirst("name"));
		
		if (formParams.getFirst("description") != null)
			group.setDescription(formParams.getFirst("description"));

		groupDao.update(group);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") int id) 
	{
		Group group = groupDao.find(id);
		if (group == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		groupDao.delete(group);
		
		return Response.success();
	}

} 


