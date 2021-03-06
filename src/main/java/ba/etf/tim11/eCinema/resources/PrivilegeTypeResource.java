package ba.etf.tim11.eCinema.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.PrivilegeTypeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.Response;


@Path("privilegetypes")
@Produces(MediaType.APPLICATION_JSON)
public class PrivilegeTypeResource extends BaseResource
{
	private DaoFactory daoFactory;
	private PrivilegeTypeDao privilegeTypeDao;
	
	
	public PrivilegeTypeResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.privilegeTypeDao = daoFactory.getPrivilegeTypeDao();
	}
	
	
	@GET
	@Privilege("List")
	public Object getAllPrivilegeTypes() 
	{
		return Response.entity(privilegeTypeDao.findAll(offset, limit));
	}
	
} 