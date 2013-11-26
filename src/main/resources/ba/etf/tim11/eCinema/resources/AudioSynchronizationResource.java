package ba.etf.tim11.eCinema.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MultivaluedMap;

import ba.etf.tim11.eCinema.dao.AudioSynchronizationDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.AudioSynchronization;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Language;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;

public class AudioSynchronizationResource
{
	private DaoFactory daoFactory;
	private AudioSynchronizationDao audioSynchronizationDao;
	
	public AudioSynchronizationResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.audioSynchronizationDao = daoFactory.getAudioSynchronizationDao();
	}
	
	@GET
	@Privilege("List")
	public List<AudioSynchronization> getAllAudioSynchronizations() 
	{
		return audioSynchronizationDao.findAll();
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public AudioSynchronization getAudioSynchronization(@PathParam("id") int id) 
	{
		AudioSynchronization audioSynchronization = audioSynchronizationDao.find(id);
		
		if (audioSynchronization == null) {
			throw new ResourceNotFoundException("AudioSynchronization not found");
		}
		
		return audioSynchronization;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public AudioSynchronization createNewAudioSynchronization(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "content", "language", "fileId") ||
			!ResourceUtil.isInt(formParams.getFirst("content")) ||
			!ResourceUtil.isInt(formParams.getFirst("language"))) {
			throw new BadRequestException("You are missing some fields.");
		}
		
		AudioSynchronization audioSynchronization = new AudioSynchronization();
		
		audioSynchronization.setFileId(formParams.getFirst("fileId"));
		
		Content content = new Content();
		content.setId(Integer.parseInt(formParams.getFirst("content")));
		audioSynchronization.setContent(content);
		
		Language language = new Language();
		language.setId(Integer.parseInt(formParams.getFirst("language")));
		audioSynchronization.setLanguage(language);

		audioSynchronizationDao.insert(audioSynchronization);
		
		return audioSynchronization;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateAudioSynchronization(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		AudioSynchronization audioSynchronization =audioSynchronizationDao.find(id);
		
		if (audioSynchronization == null) {
			throw new ResourceNotFoundException("AudioSynchronization not found.");
		}
		
		if (formParams.getFirst("content") != null)
		{
			Content content = new Content();
			content.setId(Integer.parseInt(formParams.getFirst("content")));
			audioSynchronization.setContent(content);
		}
					
		if (formParams.getFirst("language") != null)
		{
			Language language = new Language();
			language.setId(Integer.parseInt(formParams.getFirst("language")));
			audioSynchronization.setLanguage(language);
		}
			
		audioSynchronization.setFileId(formParams.getFirst("fileId"));
		
		audioSynchronizationDao.update(audioSynchronization);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteAudioSynchronization(@PathParam("id") int id) 
	{
		AudioSynchronization audioSynchronization = audioSynchronizationDao.find(id);
		if (audioSynchronization == null) {
			throw new ResourceNotFoundException("AudioSynchronization not found.");
		}
		
		audioSynchronizationDao.delete(audioSynchronization);
		
		return Response.success();
	}

}
