package ba.etf.tim11.eCinema.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ba.etf.tim11.eCinema.dao.CinemaDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Cinema;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;


@Path("cinemas")
@Produces(MediaType.APPLICATION_JSON)
public class CinemaResource extends BaseResource
{
	private DaoFactory daoFactory;
	private CinemaDao cinemaDao;
	
	
	public CinemaResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.cinemaDao = daoFactory.getCinemaDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<Cinema> getAllGroups() 
	{
		return cinemaDao.findAll(offset, limit);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Cinema getGroup(@PathParam("id") int id) 
	{
		Cinema cinema = cinemaDao.find(id);
		
		if (cinema == null) {
			throw new ResourceNotFoundException("Cinema not found");
		}
		
		return cinema;
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteCinema(@PathParam("id") Integer id) 
	{
		Cinema cinema= cinemaDao.find(id);
		if (cinema == null) {
			throw new ResourceNotFoundException("Cinema not found.");
		}
		
		cinemaDao.delete(cinema);
		
		return Response.success();
	}
}
