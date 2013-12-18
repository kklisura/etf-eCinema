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

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.service.LoginService;
import ba.etf.tim11.eCinema.service.impl.ServiceFactory;
import ba.etf.tim11.eCinema.utils.DaoUtil;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends BaseResource
{
	private static LoginService loginService = ServiceFactory.getLoginService();
	
	private DaoFactory daoFactory;
	private UserDao userDao;
	private StateDao stateDao;

	
	public UserResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		
		this.userDao = daoFactory.getUserDao();
		this.stateDao = daoFactory.getStateDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<User> getAllUsers() 
	{ 
		return userDao.findAll(offset, limit);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public User getUser(@PathParam("id") int id) 
	{
		User user = userDao.find(id);
		
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		return user;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public User createNewUser(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "password", "lastName", "firstName", "username", "email", "state", "dateOfBirth") ||
			!ResourceUtil.isInt(formParams.getFirst("state"))) 
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		int stateId = Integer.parseInt(formParams.getFirst("state"));
		
		State state = stateDao.find(stateId);
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
		user.setAddress(formParams.getFirst("address"));
		user.setPlaceOfBirth(formParams.getFirst("placeOfBirth"));
		user.setPassword(password);
		user.setSalt(salt);
		user.setState(state);
		
		try 
		{
			Date dateOfBirth = DaoUtil.string2Date(formParams.getFirst("dateOfBirth"));
			user.setDateOfBirth(DaoUtil.utilDate2SqlDatw(dateOfBirth));
		} catch (ParseException e) {
			throw new BadRequestException("Bad date format.", "Date should be in dd-MM-yyyy format.");
		}
		
		userDao.insert(user);
		
		return user;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateUser(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		User user = userDao.find(id);
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
		
		if (formParams.getFirst("address") != null)
			user.setAddress(formParams.getFirst("address"));
		
		if (formParams.getFirst("placeOfBirth") != null)
			user.setPlaceOfBirth(formParams.getFirst("placeOfBirth"));
		
		if (formParams.getFirst("password") != null)
		{
			Integer salt = ResourceUtil.generateSalt();
			
			String plainPassword = formParams.getFirst("password").concat(salt.toString());
			String password = loginService.encryptPassword(plainPassword);
			
			user.setPassword(password);
			user.setSalt(salt);
		}
		
		if (ResourceUtil.isInt(formParams.getFirst("state")))
		{
			int stateId = Integer.parseInt(formParams.getFirst("state"));
			
			State state = stateDao.find(stateId);
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
				throw new BadRequestException("Bad date format.", "Date should be in dd-MM-yyyy format.");
			}
		}
		
		userDao.update(user);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteUser(@PathParam("id") int id) 
	{
		User user = userDao.find(id);
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		userDao.delete(user);
		
		return Response.success();
	}
	
} 
