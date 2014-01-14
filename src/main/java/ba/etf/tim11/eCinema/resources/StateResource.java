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

import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.service.ServiceFactory;
import ba.etf.tim11.eCinema.service.StateService;
import ba.etf.tim11.eCinema.service.impl.ServiceFactoryImpl;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("states")
@Produces(MediaType.APPLICATION_JSON)
public class StateResource extends BaseResource
{
	private ServiceFactory serviceFactory;
	private StateService stateService;
	
	
	public StateResource()
	{
		serviceFactory = ServiceFactoryImpl.getInstance();
		stateService = serviceFactory.getStateService();
	}
	
	
	@GET
	@Privilege("List")
	public Object getAllStates() {
		return Response.entity(stateService.findAll(offset, limit));
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Object getState(@PathParam("id") int id) 
	{
		State state = stateService.find(id);
		if (state == null) {
			throw new ResourceNotFoundException("State not found.");
		}
		
		return Response.entity(state);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewState(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "name", "shortName")) {
			throw new BadRequestException("You are missing name and/or shortName field.");
		}
		
		State state = new State();
		
		state.setName(formParams.getFirst("name"));
		state.setShortName(formParams.getFirst("shortName"));
		
		stateService.insert(state);
		
		return Response.redirect(this, state.getId());
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Object updateState(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{
		State state = stateService.find(id);
		if (state == null) {
			throw new ResourceNotFoundException("State not found.");
		}
		
		if (formParams.getFirst("name") != null)
			state.setName(formParams.getFirst("name"));
		if (formParams.getFirst("shortName") != null)
			state.setShortName(formParams.getFirst("shortName"));
		
		stateService.update(state);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteState(@PathParam("id") int id) 
	{
		State state = stateService.find(id);
		if (state == null) {
			throw new ResourceNotFoundException("State not found.");
		}
		
		stateService.delete(state);
		
		return Response.success();
	}
	
} 