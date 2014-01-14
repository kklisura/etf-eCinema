package ba.etf.tim11.eCinema.resources;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

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
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ProjectionDao;
import ba.etf.tim11.eCinema.dao.ProjectionTypeDao;
import ba.etf.tim11.eCinema.dao.ReservationDao;
import ba.etf.tim11.eCinema.dao.SeatDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.ProjectionType;
import ba.etf.tim11.eCinema.models.Reservation;
import ba.etf.tim11.eCinema.models.Seat;
import ba.etf.tim11.eCinema.models.Tag;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.DaoUtil;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("projections")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectionResource extends BaseResource
{
	private DaoFactory daoFactory;
	private ProjectionDao projectionDao;
	private ContentDao contentDao;
	private CinemaHallDao cinemaHallDao;
	private ProjectionTypeDao projectionTypeDao;
	private SeatDao seatDao;
	private ReservationDao reservationDao;
	
	
	public ProjectionResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.projectionDao = daoFactory.getProjectionDao();
		this.contentDao = daoFactory.getContentDao();
		this.cinemaHallDao = daoFactory.getCinemaHallDao();
		this.projectionTypeDao = daoFactory.getProjectionTypeDao();
		this.seatDao = daoFactory.getSeatDao();
		this.reservationDao = daoFactory.getReservationDao();
	}
	
	
	@GET
	@Path("{content_id}")
	@Privilege("List")
	public Object getProjections(@PathParam("content_id") int id) 
	{ 
		return Response.entity(projectionDao.findAllByContent(id, 0, 99999));
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewProjection(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "time", "pricePerSite", "content", "cinemahall", "projectiontype") ||
			!ResourceUtil.isInt(formParams.getFirst("cinemahall")) ||
			!ResourceUtil.isInt(formParams.getFirst("projectiontype")) ||
			!ResourceUtil.isInt(formParams.getFirst("content")))
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		int contentId = Integer.parseInt(formParams.getFirst("content"));
		
		Content content = contentDao.find(contentId);
		if (content == null) {
			throw new ResourceNotFoundException("Content not found.");
		}
		
		CinemaHall cinemaHall = cinemaHallDao.find(Integer.parseInt(formParams.getFirst("cinamehall")));
		if (cinemaHall == null) {
			throw new ResourceNotFoundException("Cinema hall not found.");
		}
		
		ProjectionType projectionType = projectionTypeDao.find(Integer.parseInt(formParams.getFirst("projectiontype")));
		if (projectionType == null) {
			throw new ResourceNotFoundException("Projection type not found.");
		}
		

		Projection projection = new Projection();
		
		try 
		{
			Date time = DaoUtil.string2Date(formParams.getFirst("time"));
			projection.setTime(time);
		} catch (ParseException e) {
			throw new BadRequestException("Bad date format. Date should be in dd-MM-yyyy format.");
		}
		
		BigDecimal price = new BigDecimal(formParams.getFirst("pricePerSeat"));
		
		projection.setPricePerSeat(price);
		projection.setContent(content);
		projection.setCinemaHall(cinemaHall);
		projection.setProjectionType(projectionType);
		
		return Response.redirect(this, projection.getId());	
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Object updateProjection(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		Projection projection = projectionDao.find(id) ;
		if (projection == null) {
			throw new ResourceNotFoundException("Projection not found.");
		}
		
		if(formParams.getFirst("time") != null)
		{
			try 
			{
				Date time = DaoUtil.string2Date(formParams.getFirst("time"));
				projection.setTime(DaoUtil.utilDate2SqlDatw(time));
			} catch (ParseException e) {
				throw new BadRequestException("Bad date format. Date should be in dd-MM-yyyy format.");
			}
		}

		if(formParams.getFirst("cinemahall") != null)
		{
			CinemaHall cinemaHall = cinemaHallDao.find(Integer.parseInt(formParams.getFirst("cinamehall")));
			if (cinemaHall == null) {
				throw new ResourceNotFoundException("Cinema hall not found.");
			}
			
			projection.setCinemaHall(cinemaHall);
		}
		
		if(ResourceUtil.isInt(formParams.getFirst("projectiontype")))
		{
			ProjectionType projectionType = projectionTypeDao.find(Integer.parseInt(formParams.getFirst("projectiontype")));
			if (projectionType == null) {
				throw new ResourceNotFoundException("Projection type not found.");
			}
			
			projectionType.setId(Integer.parseInt(formParams.getFirst("projectiontype")));
			projection.setProjectionType(projectionType);
		}
		
		projectionDao.update(projection);
		
		return Response.success();

	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteProjection(@PathParam("id") Integer id) 
	{
		Projection projection = projectionDao.find(id);
		if (projection== null) {
			throw new ResourceNotFoundException("Projection not found.");
		}
		
		projectionDao.delete(projection);
		
		return Response.success();
	}
	
	@GET
	@Path("{id}/takenseats")
	public Object getTakenSeats(@PathParam("id") int id) 
	{		
		return Response.entity(seatDao.findByProjection(id, 0, 99999));
	}
	
	@POST
	@Path("{id}/takeseats")
	@Consumes("application/x-www-form-urlencoded")
	public Object takeSeats(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{		
		Projection p = new Projection();
		p.setId(id);
		
		String seatRow = null;
		int i = 0;
		while((seatRow = formParams.getFirst("seats[" + i + "][row]")) != null) {

			int row = Integer.parseInt(seatRow);
			String seatCol = formParams.getFirst("seats[" + i + "][col]");
			int col = Integer.parseInt(seatCol);
			
			Seat s= new Seat();
			s.setRow(row);
			s.setCol(col);
			Reservation r = new Reservation();
			
			r.setUser(getCurrentUser());
			r.setProjection(p);
			reservationDao.insert(r);
			
			s.setReservation(r);
			
			seatDao.insert(s);
			
			i++;
		}
		
		return Response.success();
	}
	
}
