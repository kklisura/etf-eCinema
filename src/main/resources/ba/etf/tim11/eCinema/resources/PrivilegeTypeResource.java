package ba.etf.tim11.eCinema.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.PrivilegeTypeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.PrivilegeType;


@Path("privilegetypes")
@Produces(MediaType.APPLICATION_JSON)
public class PrivilegeTypeResource
{
	private DaoFactory daoFactory;
	private PrivilegeTypeDao privilegeTypeDao;
	
	
	public PrivilegeTypeResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.privilegeTypeDao = daoFactory.getPrivilegeTypeDao();
	}
	
	
	@GET
	public List<PrivilegeType> getAllPrivilegeTypes() 
	{
		return privilegeTypeDao.findAll();
	}
	
	@GET
	@Path("{id}")
	public PrivilegeType getPrivilegeType(@PathParam("id") int id) 
	{
		return privilegeTypeDao.find(id);
	}
	
	@POST
	public void createNewPrivilegeType() 
	{
		
	}
	
	@DELETE
	@Path("{id}")
	public void deletePrivilegeType(@PathParam("id") int id) 
	{
		
	}

} 