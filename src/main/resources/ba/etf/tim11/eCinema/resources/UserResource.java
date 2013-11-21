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
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.utils.DaoUtil;


@Path("users")
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
	@Path("{username}")
	public User getUser(@PathParam("username") String username) 
	{
		return userDao.find(username);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public User createNewUser(MultivaluedMap<String, String> formParams) 
	{
		Integer salt = 0 + (int)(Math.random()*10000000); 
		
		String plainPassword = formParams.getFirst("password").concat(salt.toString());
		
		String password = DaoUtil.encryptPassword(plainPassword);
		
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
		
		State state = new State();
		state.setId(Integer.parseInt(formParams.getFirst("state")));
		user.setState(state);
		
		try {
			Date dateOfBirth = DaoUtil.string2Date(formParams.getFirst("dateOfBirth"));
			user.setDateOfBirth(DaoUtil.utilDate2SqlDatw(dateOfBirth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return userDao.insert(user);
	}
	
	@DELETE
	@Path("{username}")
	public void deleteUser(@PathParam("username") String username) 
	{
		User user = userDao.find(username);
		userDao.delete(user);
	}
} 
