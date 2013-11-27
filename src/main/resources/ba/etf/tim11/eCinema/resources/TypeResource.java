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
import ba.etf.tim11.eCinema.dao.TypeDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Type;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("types")
@Produces(MediaType.APPLICATION_JSON)
public class TypeResource extends BaseResource
{
	private DaoFactory daoFactory;
	private TypeDao typeDao;
	
	
	public TypeResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.typeDao = daoFactory.getTypeDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<Type> getAllTypes() 
	{
		return typeDao.findAll(offset, limit);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Type getType(@PathParam("id") int id) 
	{
		Type type = typeDao.find(id);
		
		if (type == null) {
			throw new ResourceNotFoundException("Type not found");
		}
		
		return type;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Type createNewType(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "type")) {
			throw new BadRequestException("You are missing some fields.");
		}
		
		Type type = new Type();
		
		type.setType(formParams.getFirst("type"));
		
		typeDao.insert(type);
		
		return type;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateType(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Type type = typeDao.find(id);
		if (type == null) {
			throw new ResourceNotFoundException("Type not found.");
		}
		
		if (formParams.getFirst("type") != null)
			type.setType(formParams.getFirst("type"));
		
		typeDao.update(type);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteType(@PathParam("id") int id) 
	{
		Type type = typeDao.find(id);
		if (type == null) {
			throw new ResourceNotFoundException("Type not found.");
		}
		
		typeDao.delete(type);
		
		return Response.success();
	}

}
