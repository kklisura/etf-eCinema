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
import ba.etf.tim11.eCinema.dao.ReservationTypeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.ReservationType;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("reservationtypes")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationTypeResource extends BaseResource
{
	private DaoFactory daoFactory;
	private ReservationTypeDao reservationTypeDao;
	
	
	public ReservationTypeResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.reservationTypeDao = (ReservationTypeDao) daoFactory.getReservationDao();
	}
	

	@GET
	@Privilege("List")
	public Object getAllReservationTypes() 
	{ 
		return Response.entity(reservationTypeDao.findAll(offset, limit));
	}
	
	@GET
	@Path("{type}")
	@Privilege("Read")
	public Object getReservationType(@PathParam("type") String type) 
	{
		ReservationType reservationType = reservationTypeDao.find(type);
		
		if (type == null) {
			throw new ResourceNotFoundException("Reservation type not found.");
		}
		
		return Response.entity(reservationType);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewReservationType(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "type")) 
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		ReservationType reservationType = new ReservationType();
		
		reservationType.setType(formParams.getFirst("type"));
		
		reservationTypeDao.insert(reservationType);
		
		return Response.redirect(this, reservationType.getId());
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Object updateReservationType(@PathParam("id") Integer id, MultivaluedMap<String, String> formParams) 
	{
		ReservationType reservationType = reservationTypeDao.find(id);
		if (reservationType == null) {
			throw new ResourceNotFoundException("Reservation type not found.");
		}
		
		if (formParams.getFirst("type") != null)
			reservationType.setType(formParams.getFirst("type"));
		
		reservationTypeDao.update(reservationType);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteReservationType(@PathParam("id") Integer id) 
	{
		ReservationType reservationType = reservationTypeDao.find(id);
		if (reservationType == null) {
			throw new ResourceNotFoundException("Reservation type not found.");
		}
		
		reservationTypeDao.delete(reservationType);
		
		return Response.success();
	}
	
}
