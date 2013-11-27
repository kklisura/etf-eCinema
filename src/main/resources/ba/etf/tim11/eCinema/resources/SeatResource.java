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

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.SeatDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.models.Reservation;
import ba.etf.tim11.eCinema.models.Seat;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("seats")
@Produces(MediaType.APPLICATION_JSON)
public class SeatResource extends BaseResource
{
	private DaoFactory daoFactory;
	private SeatDao seatDao;
	
	
	public SeatResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.seatDao = daoFactory.getSeatDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<Seat> getAllSeats() 
	{
		return seatDao.findAll(offset, limit);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Seat createNewSeat(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "seatNumber", "cinemahall", "reservation") ||
			!ResourceUtil.isInt(formParams.getFirst("cinemahall")) || 
			!ResourceUtil.isInt(formParams.getFirst("rezervation"))) 
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		Seat seat = new Seat();
		
		seat.setSeatNumber(Integer.parseInt(formParams.getFirst("seatNumber")));
		
		CinemaHall cinemaHall = new CinemaHall();
		cinemaHall.setId(Integer.parseInt(formParams.getFirst("cinemahall")));
		seat.setCinemaHall(cinemaHall);
		
		Reservation reservation = new Reservation();
		reservation.setId(Integer.parseInt(formParams.getFirst("reservation")));
		seat.setReservation(reservation);
		
		seatDao.insert(seat);
		
		return seat;
		
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateSeat(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		Seat seat = seatDao.find(id);
		
		if (seat == null) {
			throw new ResourceNotFoundException("Seat not found.");
		}
		
		if (formParams.getFirst("seatNumber") != null)
			seat.setSeatNumber(Integer.parseInt(formParams.getFirst("seatNumber")));
		
		if (formParams.getFirst("cinemahall") != null)
		{
			CinemaHall cinemaHall = new CinemaHall();
			cinemaHall.setId(Integer.parseInt(formParams.getFirst("cinemahall")));
			seat.setCinemaHall(cinemaHall);
		}
		
		if (formParams.getFirst("reservation") != null)
		{
			Reservation reservation = new Reservation();
			reservation.setId(Integer.parseInt(formParams.getFirst("reservation")));
			seat.setReservation(reservation);
		}
		
		seatDao.update(seat);
		
		return Response.success();

	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteSeat(@PathParam("id") Integer id) 
	{
		Seat seat = seatDao.find(id);
		
		if (seat == null) {
			throw new ResourceNotFoundException("Seat not found.");
		}
		
		seatDao.delete(seat);
		
		return Response.success();
	}
}
