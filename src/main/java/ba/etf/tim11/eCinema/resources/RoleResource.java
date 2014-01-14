package ba.etf.tim11.eCinema.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.dao.PrivilegeTypeDao;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.models.Privilege;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.models.User;
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
	private GroupDao groupDao;
	private PrivilegeDao privilegeDao;
	private ResourceDao resourceDao;
	private PrivilegeTypeDao privilegeTypeDao;  
	
	
	public RoleResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.roleDao = daoFactory.getRoleDao();
		this.userDao = daoFactory.getUserDao();
		this.groupDao = daoFactory.getGroupDao();
		this.privilegeDao = daoFactory.getPrivilegeDao();
		this.resourceDao = daoFactory.getResourceDao();
		this.privilegeTypeDao = daoFactory.getPrivilegeTypeDao();
	}
	
	
	@GET
	@ba.etf.tim11.eCinema.resources.privileges.Privilege("List")
	public Object getAllRoles(@QueryParam("privileges") @DefaultValue("false") boolean loadPrivileges) 
	{
		List<Role> roles = roleDao.findAll(offset, limit);
		
		if (loadPrivileges) 
		{
			for(Role role : roles) {
				List<Privilege> privileges = privilegeDao.findAllByRole(role.getId(), 0, 999999);
				
				for(Privilege priv : privileges) {
					priv.setResource(resourceDao.find(priv.getResource().getId()));
					priv.setPrivilegeType(privilegeTypeDao.find(priv.getPrivilegeType().getId()));
				}
				
				role.setPrivileges(privileges);
			}
		}
		
		return Response.entity(roles);
	}
	
	@GET
	@Path("{id}")
	@ba.etf.tim11.eCinema.resources.privileges.Privilege("Read")
	public Object getRole(@PathParam("id") int id) 
	{
		return Response.entity(roleDao.find(id));
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@ba.etf.tim11.eCinema.resources.privileges.Privilege("Create")
	public Object createNewRole(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "name", "description")) {
			throw new BadRequestException("Your missing name and/or description fields.");
		}
		
		Role role = new Role();
		role.setName(formParams.getFirst("name"));
		role.setDescription(formParams.getFirst("description"));
		
		roleDao.insert(role);
		
		return Response.redirect(this, role.getId());
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@ba.etf.tim11.eCinema.resources.privileges.Privilege("Update")
	public Object updateRole(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
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
	@ba.etf.tim11.eCinema.resources.privileges.Privilege("Delete")
	public Object deleteRole(@PathParam("id") int id) 
	{
		Role role = roleDao.find(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role is unknwon.");
		}
		
		roleDao.delete(role);
		
		return Response.success();
	}

	@GET
	@Path("users/{id}")
	@ba.etf.tim11.eCinema.resources.privileges.Privilege("Read")
	public Object getUserRoles(@PathParam("id") int id) 
	{
		User user = userDao.find(id);
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		return Response.entity(roleDao.findAllByUser(id));
	}
	
	@GET
	@Path("groups/{id}")
	@ba.etf.tim11.eCinema.resources.privileges.Privilege("Read")
	public Object getGroupRoles(@PathParam("id") int id) 
	{		
		Group group = groupDao.find(id);
		if (group == null) {
			throw new ResourceNotFoundException("Group not found.");
		}
		
		return Response.entity(roleDao.findAllByGroup(id));
	}
	
} 