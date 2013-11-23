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


@Path("states")
@Produces(MediaType.APPLICATION_JSON)
public class StateResource
{
	private DaoFactory daoFactory;
	private StateDao stateDao;
	
	
	public StateResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.stateDao = daoFactory.getStateDao();
	}
	
	
	@GET
	public List<State> getAllStates() 
	{
		return stateDao.findAll();
	}
	
	@GET
	@Path("{id}")
	public State getState(@PathParam("id") int id) 
	{
		return stateDao.find(id);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public State createNewState(MultivaluedMap<String, String> formParams) 
	{
		State state = new State();
		
		state.setName(formParams.getFirst("name"));
		state.setShortName(formParams.getFirst("shortName"));
		
		stateDao.insert(state);
		
		return state;
	}
	
	@DELETE
	@Path("{id}")
	public void deleteState(@PathParam("id") int id) 
	{
		State state = stateDao.find(id);
		stateDao.delete(state);
	}

} 