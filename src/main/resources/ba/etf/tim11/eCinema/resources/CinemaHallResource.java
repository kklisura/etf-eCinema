package ba.etf.tim11.eCinema.resources;

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

import ba.etf.tim11.eCinema.dao.CinemaHallDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Cinema;
import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("cinemahalls")
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
	
	@GET
	@Privilege("List")
	public List<CinemaHall> getAllCinemaHalls() 
	{ 
		return cinemaHallDao.findAll();
	}
	
	@GET
	@Path("{title}")
	@Privilege("Read")
	public CinemaHall getCinemaHall(@PathParam("title") String title) 
	{
		CinemaHall cinemaHall = cinemaHallDao.find(title);
		
		if (title == null) {
			throw new ResourceNotFoundException("Cinema hall not found.");
		}
		
		return cinemaHall;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public CinemaHall createNewCinemaHall(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "title", "cinema", "numberOfSeat") ||
			!ResourceUtil.isInt(formParams.getFirst("cinema"))) 
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		CinemaHall cinemaHall = new CinemaHall();
		
		cinemaHall.setTitle(formParams.getFirst("title"));
		
		Cinema cinema = new Cinema();
		cinema.setId(Integer.parseInt(formParams.getFirst("cinema")));
		cinemaHall.setCinema(cinema);
		
		cinemaHall.setNumberOfSeat(Integer.parseInt(formParams.getFirst("numberOfSeat")));
		return cinemaHall;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateUser(@PathParam("id") Integer id, MultivaluedMap<String, String> formParams) 
	{
		CinemaHall cinemaHall = cinemaHallDao.find(id);
		if (cinemaHall == null) {
			throw new ResourceNotFoundException("Cinema hall not found.");
		}
		
		if (formParams.getFirst("title") != null)
			cinemaHall.setTitle(formParams.getFirst("title"));
		
		if (formParams.getFirst("cinema") != null)
		{
			Cinema cinema = new Cinema();
			cinema.setId(Integer.parseInt(formParams.getFirst("cinema")));
			cinemaHall.setCinema(cinema);
		}
		
		if (formParams.getFirst("numberOfSeat") != null)
			cinemaHall.setNumberOfSeat(Integer.parseInt(formParams.getFirst("numberOfSeat")));
		
		return Response.success();

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
