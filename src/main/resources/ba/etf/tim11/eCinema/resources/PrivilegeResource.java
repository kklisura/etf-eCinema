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
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Privilege;


@Path("privileges")
@Produces(MediaType.APPLICATION_JSON)
public class PrivilegeResource {
	private DaoFactory daoFactory;
	private PrivilegeDao privilegeDao;
	
	public PrivilegeResource(){
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.privilegeDao = daoFactory.getPrivilegeDao();
	}
	@GET
	public List<Privilege> getAllPrivileges() 
	{
		return privilegeDao.findAll();
	}

	@GET
	@Path("{id}")
	public Privilege getPrivileg(@PathParam("id") int id) 
	{
		return privilegeDao.find(id);
	}

	@POST
	public void createNewPrivilege() 
	{
		
	}

	@DELETE
	@Path("{id}")
	public void deletePrivilege(@PathParam("id") int id) 
	{
		
	}
} 




