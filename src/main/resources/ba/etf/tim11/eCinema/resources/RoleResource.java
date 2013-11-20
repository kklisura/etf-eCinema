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
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Role;


@Path("roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource
{
	private DaoFactory daoFactory;
	private RoleDao roleDao;
	
	
	public RoleResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.roleDao = daoFactory.getRoleDao();
	}
	
	
	@GET
	public List<Role> getAllRoles() 
	{
		return roleDao.findAll();
	}
	
	@GET
	@Path("{id}")
	public Role getRole(@PathParam("id") int id) 
	{
		return roleDao.find(id);
	}
	
	@POST
	public void createNewRole() 
	{
		
	}
	
	@DELETE
	@Path("{id}")
	public void deleteRole(@PathParam("id") int id) 
	{
		
	}

} 