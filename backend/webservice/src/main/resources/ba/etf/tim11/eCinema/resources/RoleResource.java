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
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource extends BaseResource
{
	private DaoFactory daoFactory;
	private RoleDao roleDao;
	private UserDao userDao;
	
	
	public RoleResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.roleDao = daoFactory.getRoleDao();
		this.userDao = daoFactory.getUserDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<Role> getAllRoles() 
	{
		return roleDao.findAll(offset, limit);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Role getRole(@PathParam("id") int id) 
	{
		return roleDao.find(id);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Role createNewRole(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "name", "description")) {
			throw new BadRequestException("Your missing name and/or description fields.");
		}
		
		Role role = new Role();
		role.setName(formParams.getFirst("name"));
		role.setDescription(formParams.getFirst("description"));
		
		roleDao.insert(role);
		
		return role;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateRole(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		Role role = roleDao.find(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role is unknwon.");
		}
		
		if (formParams.getFirst("name") != null)
			role.setName(formParams.getFirst("name"));
		if (formParams.getFirst("description") != null)
			role.setDescription(formParams.getFirst("description"));
		
		roleDao.update(role);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteRole(@PathParam("id") int id) 
	{
		Role role = roleDao.find(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role is unknwon.");
		}
		
		roleDao.delete(role);
		
		return Response.success();
	}

	@POST
	@Path("{id}\\users")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Response addUserToRole(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.isInt(formParams.getFirst("user"))) {
			throw new BadRequestException("You are missing user field.");
		}
		
		Role role = roleDao.find(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role not found.");
		}
		
		User user = userDao.find(Integer.parseInt(formParams.getFirst("user")));
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}	
		
		return Response.success();
	}
} 