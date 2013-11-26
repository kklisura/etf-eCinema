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
import ba.etf.tim11.eCinema.dao.ProjectionTypeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.ProjectionType;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("projectionTypes")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectionTypeResource 
{
	private DaoFactory daoFactory;
	private ProjectionTypeDao projectionTypeDao;
	
	public ProjectionTypeResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.projectionTypeDao = daoFactory.getProjectionTypeDao();
	}
	
	@GET
	@Privilege("List")
	public List<ProjectionType> getAllProjectionTypes() 
	{ 
		return projectionTypeDao.findAll();
	}
	
	@GET
	@Path("{type}")
	@Privilege("Read")
	public ProjectionType getProjectionType(@PathParam("type") String type) 
	{
		ProjectionType projectionType = projectionTypeDao.find(type);
		
		if (projectionType == null) {
			throw new ResourceNotFoundException("Prrojection type not found.");
		}
		
		return projectionType;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public ProjectionType createNewProjectionType(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "type")) 
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		
		ProjectionType projectionType = new ProjectionType();
		
		projectionType.setType(formParams.getFirst("type"));
	
		
		projectionTypeDao.insert(projectionType);
		
		
		return projectionType;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateProjectionType(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		ProjectionType projectionType = projectionTypeDao.find(id);
		
		if (projectionType == null) {
			throw new ResourceNotFoundException("Projection type not found.");
		}
		
		if (formParams.getFirst("type") != null)
			projectionType.setType(formParams.getFirst("type"));
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response projectionType(@PathParam("id") int id) 
	{
		ProjectionType projectionType = projectionTypeDao.find(id);
		
		if (projectionType == null) {
			throw new ResourceNotFoundException("Projection type not found.");
		}
		
		projectionTypeDao.delete(projectionType);
		
		return Response.success();
	}
}
