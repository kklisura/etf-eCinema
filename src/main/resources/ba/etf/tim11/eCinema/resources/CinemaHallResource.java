package ba.etf.tim11.eCinema.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ba.etf.tim11.eCinema.dao.CinemaHallDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;


@Path("halls")
@Produces(MediaType.APPLICATION_JSON)
public class CinemaHallResource
{
	private DaoFactory daoFactory;
	private CinemaHallDao cinemaHallDao;
	
	
	public CinemaHallResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.cinemaHallDao = daoFactory.getCinemaHallDao();
	}
	
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteCinemaHall(@PathParam("id") Integer id) 
	{
		CinemaHall cinemaHall = cinemaHallDao.find(id);
		if (cinemaHall == null) {
			throw new ResourceNotFoundException("Cinema hall not found.");
		}
		
		cinemaHallDao.delete(cinemaHall);
		
		return Response.success();
	}
	
}
