package ba.etf.tim11.eCinema.resources;

import java.text.ParseException;
import java.util.Date;
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

import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.UserActionDao;
import ba.etf.tim11.eCinema.dao.UserActionTypeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.models.UserAction;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.service.LoginService;
import ba.etf.tim11.eCinema.service.ServiceFactory;
import ba.etf.tim11.eCinema.service.StateService;
import ba.etf.tim11.eCinema.service.UserService;
import ba.etf.tim11.eCinema.service.impl.ServiceFactoryImpl;
import ba.etf.tim11.eCinema.utils.DaoUtil;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends BaseResource
{
	private ServiceFactory serviceFactory;
	private LoginService loginService;
	private UserService userService;
	private StateService stateService;
	private GroupDao groupDao;
	private RoleDao roleDao;
	private UserActionDao userActionDao;
	private UserActionTypeDao userActionTypeDao;
	
	public UserResource()
	{
		serviceFactory = ServiceFactoryImpl.getInstance();
		userService = serviceFactory.getUserService();
		stateService = serviceFactory.getStateService();
		loginService = serviceFactory.getLoginService();
		groupDao = JDBCDaoFactory.getInstance().getGroupDao();
		roleDao = JDBCDaoFactory.getInstance().getRoleDao();
		userActionDao = JDBCDaoFactory.getInstance().getUserActionDao();
		userActionTypeDao = JDBCDaoFactory.getInstance().getUserActionTypeDao();
	}
	
	
	@GET
	@Privilege("List")
	public Object getAllUsers() {
		return Response.paginated(userService.findAll(offset, limit), 
								  offset, 
								  limit, 
								  userService.numberOfUsers());
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Object getUser(@PathParam("id") int id) 
	{	
		User user = userService.find(id);
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		return Response.entity(user);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewUser(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "password", "lastName", "firstName", "phone", "username", "email", "state[id]", "dateOfBirth"))
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		int stateId = Integer.parseInt(formParams.getFirst("state[id]"));
		
		State state = stateService.find(stateId);
		if (state == null) {
			throw new BadRequestException("State is unknown.");
		}
		
		Integer salt = ResourceUtil.generateSalt();
		
		String plainPassword = formParams.getFirst("password").concat(salt.toString());
		String password = loginService.encryptPassword(plainPassword);
		
		User user = new User();
	
		user.setLastName(formParams.getFirst("lastName"));
		user.setFirstName(formParams.getFirst("firstName"));
		user.setUsername(formParams.getFirst("username"));
		user.setEmail(formParams.getFirst("email"));
		user.setPhone(formParams.getFirst("phone"));
		user.setPassword(password);
		user.setSalt(salt);
		user.setState(state);
		
		try 
		{
			Date dateOfBirth = DaoUtil.string2Date(formParams.getFirst("dateOfBirth"));
			user.setDateOfBirth(DaoUtil.utilDate2SqlDatw(dateOfBirth));
		} catch (ParseException e) {
			throw new BadRequestException("Bad date format. Date should be in dd-MM-yyyy format.");
		}
		
		userService.insert(user);
		
		return Response.redirect(this, user.getId());
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Object updateUser(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		User user = userService.find(id);
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		if (formParams.getFirst("lastName") != null)
			user.setLastName(formParams.getFirst("lastName"));
		
		if (formParams.getFirst("firstName") != null)
			user.setFirstName(formParams.getFirst("firstName"));
		
		if (formParams.getFirst("email") != null)
			user.setEmail(formParams.getFirst("email"));
		
		if (formParams.getFirst("phone") != null)
			user.setPhone(formParams.getFirst("phone"));
		
		if (formParams.getFirst("password") != null)
		{
			Integer salt = ResourceUtil.generateSalt();
			
			String plainPassword = formParams.getFirst("password").concat(salt.toString());
			String password = loginService.encryptPassword(plainPassword);
			
			user.setPassword(password);
			user.setSalt(salt);
		}
		
		if (ResourceUtil.isInt(formParams.getFirst("state[id]")))
		{
			int stateId = Integer.parseInt(formParams.getFirst("state[id]"));
			
			State state = stateService.find(stateId);
			if (state == null) {
				throw new BadRequestException("State is unknown.");
			}
			
			user.setState(state);
		}
		
		if (formParams.getFirst("dateOfBirth") != null)
		{
			try 
			{
				Date dateOfBirth = DaoUtil.string2Date(formParams.getFirst("dateOfBirth"));
				user.setDateOfBirth(DaoUtil.utilDate2SqlDatw(dateOfBirth));
			} catch (ParseException e) {
				throw new BadRequestException("Bad date format. Date should be in yyyy-MM-dd format.");
			}
		}
		
		String groupName = null;
		int i = 0;
		while((groupName = formParams.getFirst("groups[" + i + "][name]")) != null) {
			Group group = groupDao.find(groupName);
			user.addToGroup(group);
			groupDao.update(group);
			i++;
		}
		
		String roleName = null;
		i = 0;
		while((roleName = formParams.getFirst("roles[" + i + "][name]")) != null) {
			Role role = roleDao.find(roleName);
			user.addRole(role);
			i++;
		}
		
		userService.update(user);

		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteUser(@PathParam("id") int id) 
	{
		User user = userService.find(id);
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		userService.delete(user);
		
		return Response.success();
	}
	
	@GET
	@Path("{id}/history")
	@Privilege("List")
	public Object getUserHistory(@PathParam("id") int id) {
		List<UserAction> uac = userActionDao.findAllByUser(id, 0, 999999);
		for(UserAction ua : uac) {
			ua.setUserActionType(userActionTypeDao.find(ua.getUserActionType().getId()));
		}
		return Response.entity(uac);
	}
	
} 
