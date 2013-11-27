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
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("states")
@Produces(MediaType.APPLICATION_JSON)
public class StateResource extends BaseResource
{
	private DaoFactory daoFactory;
	private StateDao stateDao;
	
	
	public StateResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.stateDao = daoFactory.getStateDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<State> getAllStates() 
	{
		return stateDao.findAll(offset, limit);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public State getState(@PathParam("id") int id) 
	{
		State state = stateDao.find(id);
		
		if (state == null) {
			throw new ResourceNotFoundException("State not found.");
		}
		
		return state;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public State createNewState(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "name", "shortName")) {
			throw new BadRequestException("You are missing name and/or shortName field.");
		}
		
		State state = new State();
		
		state.setName(formParams.getFirst("name"));
		state.setShortName(formParams.getFirst("shortName"));
		
		stateDao.insert(state);
		
		return state;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateState(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		State state = stateDao.find(id);
		if (state == null) {
			throw new ResourceNotFoundException("State not found.");
		}
		
		if (formParams.getFirst("name") != null)
			state.setName(formParams.getFirst("name"));
		if (formParams.getFirst("shortName") != null)
			state.setShortName(formParams.getFirst("shortName"));
		
		stateDao.update(state);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteState(@PathParam("id") int id) 
	{
		State state = stateDao.find(id);
		
		if (state == null) {
			throw new ResourceNotFoundException("State not found.");
		}
		
		stateDao.delete(state);
		
		return Response.success();
	}
	
} 