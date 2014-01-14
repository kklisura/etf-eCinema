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

import ba.etf.tim11.eCinema.dao.AudioSynchronizationDao;
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.LanguageDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.AudioSynchronization;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Language;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("audiosync")
@Produces(MediaType.APPLICATION_JSON)
public class AudioSynchronizationResource extends BaseResource
{
	private DaoFactory daoFactory;
	private LanguageDao languageDao;
	private ContentDao contentDao;
	private AudioSynchronizationDao audioSynchronizationDao;
	
	
	public AudioSynchronizationResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.audioSynchronizationDao = daoFactory.getAudioSynchronizationDao();
		this.contentDao = daoFactory.getContentDao();
		this.languageDao = daoFactory.getLanguageDao();
	}
	
	
	@GET
	@Path("{content_id}")
	@Privilege("List")
	public Object getContentAudioSync(@PathParam("content_id") int contentId) 
	{
		if (contentDao.find(contentId) == null) {
			throw new ResourceNotFoundException("Content not found");
		}
		
		return Response.entity(audioSynchronizationDao.findAllByContent(contentId, offset, limit));
	}
	
	@POST
	@Path("{content_id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewAudioSynchronization(@PathParam("content_id") int contentId, MultivaluedMap<String, String> formParams) 
	{
		// TODO(kklisura): Handle upload!
		
		Content content = contentDao.find(contentId);
		if (content == null) {
			throw new ResourceNotFoundException("Content not found");
		}
		
		if (!ResourceUtil.isInt(formParams.getFirst("language"))) {
			throw new BadRequestException("You are missing language field.");
		}
		
		Language language = languageDao.find(Integer.parseInt(formParams.getFirst("language")));
		if (language == null) {
			throw new ResourceNotFoundException("Language not found");
		}
		
		AudioSynchronization audioSynchronization = new AudioSynchronization();
		
		audioSynchronization.setContent(content);		
		audioSynchronization.setLanguage(language);

		audioSynchronizationDao.insert(audioSynchronization);
		
		return Response.redirect(this, audioSynchronization.getId());
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteAudioSynchronization(@PathParam("id") int id) 
	{
		AudioSynchronization audioSynchronization = audioSynchronizationDao.find(id);
		if (audioSynchronization == null) {
			throw new ResourceNotFoundException("AudioSynchronization not found.");
		}
		
		audioSynchronizationDao.delete(audioSynchronization);
		
		return Response.success();
	}
	
}
