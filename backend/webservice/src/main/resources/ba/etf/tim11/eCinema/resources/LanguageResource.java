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
import ba.etf.tim11.eCinema.dao.LanguageDao;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Language;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("languages")
@Produces(MediaType.APPLICATION_JSON)
public class LanguageResource extends BaseResource
{
	private DaoFactory daoFactory;
	private LanguageDao languageDao;
	private StateDao stateDao;
	
	
	public LanguageResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.languageDao = daoFactory.getLanguageDao();
		this.stateDao = daoFactory.getStateDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<Language> getAllLanguages() 
	{
		return languageDao.findAll(offset, limit);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Language createNewLanguage(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "language") ||
			!ResourceUtil.isInt(formParams.getFirst("state"))) {
			throw new BadRequestException("You are missing some fields.");
		}
		
		State state = stateDao.find(Integer.parseInt(formParams.getFirst("state")));
		if (state == null) {
			throw new ResourceNotFoundException("State not found.");
		}
		
		Language language = new Language();
		
		language.setLanguage(formParams.getFirst("language"));
		language.setState(state);
		
		languageDao.insert(language);
		
		return language;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateLanguage(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Language language = languageDao.find(id);
		if (language == null) {
			throw new ResourceNotFoundException("Language not found.");
		}
		
		if (formParams.getFirst("language") != null)
			language.setLanguage(formParams.getFirst("language"));
		
		if (ResourceUtil.isInt(formParams.getFirst("state")))
		{
			State state = stateDao.find(Integer.parseInt(formParams.getFirst("state")));
			if (state == null) {
				throw new ResourceNotFoundException("State not found.");
			}
			
			language.setState(state);
		}

		languageDao.update(language);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteLanguage(@PathParam("id") int id) 
	{
		Language language = languageDao.find(id);
		if (language == null) {
			throw new ResourceNotFoundException("Language not found.");
		}
		
		languageDao.delete(language);
		
		return Response.success();
	}
	
}
