package ba.etf.tim11.eCinema.resources;

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
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("groups")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource extends BaseResource
{	
	private DaoFactory daoFactory;
	private GroupDao groupDao;
	private RoleDao roleDao;
	
	
	public GroupResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.groupDao = daoFactory.getGroupDao();
		this.roleDao = daoFactory.getRoleDao();
	}
	
	
	@GET
	@Privilege("List")
	public Object getAllGroups() 
	{
		return Response.paginated(groupDao.findAll(offset, limit), offset, limit, -1);
	}
	
	@GET
	@Path("users/{id}")
	@Privilege("List")
	public Object getUserGroups(@PathParam("id") int userId) 
	{		
		return Response.entity(groupDao.findAllByUser(userId));
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Object getGroup(@PathParam("id") int id) 
	{
		Group group = groupDao.find(id);
		if (group == null) {
			throw new ResourceNotFoundException("Group not found");
		}
		
		return Response.entity(group);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewGroup(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "name", "description")) {
			throw new BadRequestException("You are missing name and/or description field.");
		}
		
		Group group = new Group();
		
		group.setName(formParams.getFirst("name"));
		group.setDescription(formParams.getFirst("description"));

		String roleName = null;
		int i = 0;
		while((roleName = formParams.getFirst("roles[" + i + "][name]")) != null) {
			Role role = roleDao.find(roleName);
			group.addRole(role);
			i++;
			System.out.print(roleName);
		}
		
		groupDao.insert(group);
		
		return Response.redirect(this, group.getId());
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Object updateGroup(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Group group = groupDao.find(id);
		if (group == null) {
			throw new ResourceNotFoundException("Group not found.");
		}
		
		if (formParams.getFirst("name") != null)
			group.setName(formParams.getFirst("name"));
		
		if (formParams.getFirst("description") != null)
			group.setDescription(formParams.getFirst("description"));

		String roleName = null;
		int i = 0;
		while((roleName = formParams.getFirst("roles[" + i + "][name]")) != null) {
			Role role = roleDao.find(roleName);
			group.addRole(role);
			i++;
		}
		
		groupDao.update(group);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteGroup(@PathParam("id") int id) 
	{
		Group group = groupDao.find(id);
		if (group == null) {
			throw new ResourceNotFoundException("Group not found.");
		}
		
		groupDao.delete(group);
		
		return Response.success();
	}
	
} 


