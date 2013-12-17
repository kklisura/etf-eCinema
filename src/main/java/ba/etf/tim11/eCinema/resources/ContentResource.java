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

import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Type;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;

@Path("contents")
@Produces(MediaType.APPLICATION_JSON)
public class ContentResource extends BaseResource
{
	private DaoFactory daoFactory;
	private ContentDao contentDao;
	
	public ContentResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.contentDao = daoFactory.getContentDao();
	}
	
	@GET
	@Privilege("List")
	public List<Content> getAllContents() 
	{
		return contentDao.findAll(offset, limit);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Content getContent(@PathParam("id") int id) 
	{
		Content content = contentDao.find(id);
		
		if (content == null) {
			throw new ResourceNotFoundException("Content not found");
		}
		
		return content;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Content createNewContent(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "year", "length", "type") || 
			!ResourceUtil.isInt(formParams.getFirst("year")) ||
			!ResourceUtil.isInt(formParams.getFirst("length")) ||
		    !ResourceUtil.isInt(formParams.getFirst("type")))
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		Content content = new Content();
		content.setYear(Integer.parseInt(formParams.getFirst("year")));
		content.setLength(Integer.parseInt(formParams.getFirst("length")));
		
		Type type = new Type();
		type.setId(Integer.parseInt(formParams.getFirst("type")));
		content.setType(type);
		
		contentDao.insert(content);
		
		return content;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateContent(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Content content = contentDao.find(id);
		if (content == null) {
			throw new ResourceNotFoundException("Content not found.");
		}
		
		if (formParams.getFirst("year") != null)
			content.setYear(Integer.parseInt(formParams.getFirst("year")));
		
		if (formParams.getFirst("length") != null)
			content.setLength(Integer.parseInt(formParams.getFirst("length")));
		if(formParams.getFirst("type") != null)
		{
			Type type = new Type();
			type.setId(Integer.parseInt(formParams.getFirst("type")));
			content.setType(type);
		}
		
		contentDao.update(content);
		
		return Response.success();
		
	    }
		
		@DELETE
		@Path("{id}")
		@Privilege("Delete")
		public Response deleteUser(@PathParam("id") int id) 
		{
			Content content = contentDao.find(id);
			if (content == null) {
				throw new ResourceNotFoundException("Content not found.");
			}
			
			contentDao.delete(content);
			
			return Response.success();
		}
	

}
