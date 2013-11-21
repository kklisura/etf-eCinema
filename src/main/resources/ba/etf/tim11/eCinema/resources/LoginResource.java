package ba.etf.tim11.eCinema.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.utils.DaoUtil;


@Path("login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource 
{
	private DaoFactory daoFactory;
	private UserDao userDao;
	
	
	public LoginResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.userDao = daoFactory.getUserDao();
	}

	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public User doLogin(MultivaluedMap<String, String> formParams)
	{
		User user = userDao.find(formParams.getFirst("username"));
		
		if (user != null) 
		{
			String salt = Integer.toString(user.getSalt());
			String plainPassword = formParams.getFirst("password").concat(salt);

			String password = DaoUtil.encryptPassword(plainPassword);

			if (user.getPassword().equals(password)) 
			{
				return user;
			}
		}
		
		return null;
	}
	
}
