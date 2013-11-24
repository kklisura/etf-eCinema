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
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Resource;


@Path("resource")
@Produces(MediaType.APPLICATION_JSON)
public class ResourceResource 
{
	private DaoFactory daoFactory;
	private ResourceDao resourceDao;
	
	public ResourceResource(){
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.resourceDao = daoFactory.getResourceDao();
	}
	
	@GET
	public List<Resource> getAllResources() 
	{
		return resourceDao.findAll();
	}

	@GET
	@Path("{id}")
	public Resource getResource(@PathParam("id") int id) 
	{
		return resourceDao.find(id);
	}

	@POST
	public void createNewResource() 
	{
		
	}

	@DELETE
	@Path("{id}")
	public void deleteResource(@PathParam("id") int id) 
	{
		
	}
}
