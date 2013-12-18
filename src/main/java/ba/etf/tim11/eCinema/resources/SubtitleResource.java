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
import ba.etf.tim11.eCinema.dao.SubtitleDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Language;
import ba.etf.tim11.eCinema.models.Subtitle;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("subtitles")
@Produces(MediaType.APPLICATION_JSON)
public class SubtitleResource extends BaseResource
{
	private DaoFactory daoFactory;
	private SubtitleDao subtitleDao;
	
	
	public SubtitleResource ()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.subtitleDao = daoFactory.getSubtitleDao();
	}
	
	
	@GET
	@Privilege("List")
	public List<Subtitle> getAllSubtitles() 
	{
		return subtitleDao.findAll(offset, limit);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Subtitle getSubtitle(@PathParam("id") int id) 
	{
		Subtitle subtitle = subtitleDao.find(id);
		
		if (subtitle == null) {
			throw new ResourceNotFoundException("Subtitle not found");
		}
		
		return subtitle;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Subtitle createNewSubtitle(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "language", "content", "fileId") ||
			!ResourceUtil.isInt(formParams.getFirst("language")) ||
			!ResourceUtil.isInt(formParams.getFirst("content"))) {
			throw new BadRequestException("You are missing some fields.");
		}
		
		Subtitle subtitle = new Subtitle();
		
		Language language = new Language();
		language.setId(Integer.parseInt(formParams.getFirst("language")));
		subtitle.setLanguage(language);
		
		Content content = new Content();
		content.setId(Integer.parseInt(formParams.getFirst("content")));
		subtitle.setContent(content);
		
		subtitle.setFileId(formParams.getFirst("fileId"));

		subtitleDao.insert(subtitle);
		
		return subtitle;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateSubtitle(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Subtitle subtitle = subtitleDao.find(id);
		if (subtitle == null) {
			throw new ResourceNotFoundException("Subtitle not found.");
		}
		
		if (formParams.getFirst("language") != null)
		{
			Language language = new Language();
			
			language.setId(Integer.parseInt(formParams.getFirst("language")));
			subtitle.setLanguage(language);
		}
					
		if (formParams.getFirst("content") != null)
		{
			Content content = new Content();
			
			content.setId(Integer.parseInt(formParams.getFirst("content")));
			subtitle.setContent(content);
		}
		
		if (formParams.getFirst("fileId") != null)
			subtitle.setFileId(formParams.getFirst("fileId"));
			
		subtitleDao.update(subtitle);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteSubtitle(@PathParam("id") int id) 
	{
		Subtitle subtitle = subtitleDao.find(id);
		if (subtitle == null) {
			throw new ResourceNotFoundException("Subtitle not found.");
		}
		
		subtitleDao.delete(subtitle);
		
		return Response.success();
	}
	

}
