package ba.etf.tim11.eCinema.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.Response;


@Path("resources")
@Produces(MediaType.APPLICATION_JSON)
public class ResourceResource extends BaseResource
{
	private DaoFactory daoFactory;
	private ResourceDao resourceDao;
	
	
	public ResourceResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.resourceDao = daoFactory.getResourceDao();
	}
	
	
	@GET
	@Privilege("List")
	public Object getAllResources() 
	{
		return Response.entity(resourceDao.findAll(offset, limit));
	}
	
}
