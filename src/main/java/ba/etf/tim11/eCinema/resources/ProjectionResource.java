package ba.etf.tim11.eCinema.resources;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
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
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ProjectionDao;
import ba.etf.tim11.eCinema.dao.ProjectionTypeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.ProjectionType;
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
	
	
	public ProjectionResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.projectionDao = daoFactory.getProjectionDao();
		this.contentDao = daoFactory.getContentDao();
		this.cinemaHallDao = daoFactory.getCinemaHallDao();
		this.projectionTypeDao = daoFactory.getProjectionTypeDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<Projection> getAllProjections() 
	{ 
		return projectionDao.findAll(offset, limit);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Projection createNewProjection(MultivaluedMap<String, String> formParams) 
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
			throw new BadRequestException("Bad date format.", "Date should be in dd-MM-yyyy format.");
		}
		
		BigDecimal price = new BigDecimal(formParams.getFirst("pricePerSeat"));
		
		projection.setPricePerSeat(price);
		projection.setContent(content);
		projection.setCinemaHall(cinemaHall);
		projection.setProjectionType(projectionType);
		
		return projection;	
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateProjection(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
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
				throw new BadRequestException("Bad date format.", "Date should be in dd-MM-yyyy format.");
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
	public Response deleteProjection(@PathParam("id") Integer id) 
	{
		Projection projection = projectionDao.find(id);
		if (projection== null) {
			throw new ResourceNotFoundException("Projection not found.");
		}
		
		projectionDao.delete(projection);
		
		return Response.success();
	}
	
}
