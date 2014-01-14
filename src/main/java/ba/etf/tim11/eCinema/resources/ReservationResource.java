package ba.etf.tim11.eCinema.resources;

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
import ba.etf.tim11.eCinema.dao.ReservationDao;
import ba.etf.tim11.eCinema.dao.SeatDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.Receipt;
import ba.etf.tim11.eCinema.models.Reservation;
import ba.etf.tim11.eCinema.models.ReservationType;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("reservations")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource extends BaseResource
{
	private DaoFactory daoFactory;
	private ReservationDao reservationDao;
	private SeatDao seatDao;
	
	
	public ReservationResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.reservationDao = daoFactory.getReservationDao();
		this.seatDao = daoFactory.getSeatDao();
	}
	
	
	@GET
	@Privilege("List")
	public Object getAllReservations() 
	{ 
		return Response.paginated(reservationDao.findAll(offset, limit), offset, limit, -1);
	}
	
	@GET
	@Path("{projection_id}")
	@Privilege("List")
	public Object getReservationsPerProjection(@PathParam("projection_id") int projectionId) 
	{ 
		return Response.entity(reservationDao.findAllByProjection(projectionId, 0, 999999));
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewReservation(MultivaluedMap<String, String> formParams) 
	{
		// TODO(kklisura): Need to implement this.
		if (!ResourceUtil.hasAll(formParams, "user", "reservationtype") ||
			!ResourceUtil.isInt(formParams.getFirst("projection")) || !ResourceUtil.isInt(formParams.getFirst("user")) ||
			!ResourceUtil.isInt(formParams.getFirst("receipt")) || !ResourceUtil.isInt(formParams.getFirst("reservationtype"))) 
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		Reservation reservation = new Reservation();
		
		Projection projection = new Projection();
		projection.setId(Integer.parseInt(formParams.getFirst("projection")));
		reservation.setProjection(projection);
		
		User user = new User();
		user.setId(Integer.parseInt(formParams.getFirst("user")));
		reservation.setUser(user);
		
		Receipt receipt = new Receipt();
		receipt.setId(Integer.parseInt(formParams.getFirst("receipt")));
		reservation.setReceipt(receipt);
		
		ReservationType reservationType  = new ReservationType();
		reservationType.setId(Integer.parseInt(formParams.getFirst("reservationType")));
		reservation.setReservationType(reservationType);
		
		reservationDao.insert(reservation);
		
		return Response.redirect(this, reservation.getId());
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Object updateReservation(@PathParam("id") Integer id, MultivaluedMap<String, String> formParams) 
	{
		Reservation reservation= reservationDao.find(id);
		if (reservation == null) {
			throw new ResourceNotFoundException("Reservation not found.");
		}
		
		if (formParams.getFirst("projection") != null)
		{
			Projection projection = new Projection();
			projection.setId(Integer.parseInt(formParams.getFirst("projection")));
			reservation.setProjection(projection);
		}
		if (formParams.getFirst("user") != null)
		{
			User user = new User();
			user.setId(Integer.parseInt(formParams.getFirst("user")));
			reservation.setUser(user);
		}
		if (formParams.getFirst("receipt") != null)
		{
			Receipt receipt = new Receipt();
			receipt.setId(Integer.parseInt(formParams.getFirst("receipt")));
			reservation.setReceipt(receipt);
		}
		if (formParams.getFirst("reservationType") != null)
		{
			ReservationType reservationType  = new ReservationType();
			reservationType.setId(Integer.parseInt(formParams.getFirst("reservationType")));
			reservation.setReservationType(reservationType);
			
		}
		
		reservationDao.update(reservation);
		
		return Response.success();	
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteReservation(@PathParam("id") int id) 
	{
		Reservation reservation= reservationDao.find(id);
		if (reservation == null) {
			throw new ResourceNotFoundException("Reservation not found.");
		}
		
		reservationDao.delete(reservation);
		
		return Response.success();
	}
	
}
