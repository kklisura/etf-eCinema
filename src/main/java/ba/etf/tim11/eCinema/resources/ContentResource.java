package ba.etf.tim11.eCinema.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import ba.etf.tim11.eCinema.dao.ContentMarkDao;
import ba.etf.tim11.eCinema.dao.TagDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Tag;
import ba.etf.tim11.eCinema.models.TopContent;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.service.ContentService;
import ba.etf.tim11.eCinema.service.ServiceFactory;
import ba.etf.tim11.eCinema.service.impl.ServiceFactoryImpl;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("contents")
@Produces(MediaType.APPLICATION_JSON)
public class ContentResource extends BaseResource
{
	private ServiceFactory serviceFactory;
	private ContentService contentService;
	private ContentMarkDao contentMarkDao;
	private TagDao tagDao;
	
	
	public ContentResource()
	{
		this.serviceFactory = ServiceFactoryImpl.getInstance();
		this.contentService = serviceFactory.getContentService();
		this.contentMarkDao = JDBCDaoFactory.getInstance().getContentMarkDao();
		this.tagDao = JDBCDaoFactory.getInstance().getTagDao();
	}
	
	@GET
	@Privilege("List")
	public Object getAllContents(@QueryParam("filter") @DefaultValue("") String filter) 
	{
		if (filter.length() == 0) {
			return Response.paginated(contentService.findAll(offset, limit), offset, limit, -1);
		}
		
		switch(filter)
		{
		case "newest":
			return Response.paginated(contentService.findRecentlyAdded(offset, limit), offset, limit, -1);
		case "rated":
			return Response.paginated(contentService.findBestRated(offset, limit), offset, limit, -1);
		case "top":
			return Response.paginated(contentService.findTop(offset, limit), offset, limit, -1);
		case "theaters":
			return Response.paginated(contentService.findInTheaters(offset, limit), offset, limit, -1);
		}
		
		return Response.paginated(contentService.findAllByFilter(filter, offset, limit), offset, limit, -1);
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Object getContent(@PathParam("id") int id) 
	{
		Content content = contentService.find(id);
		if (content == null) {
			throw new ResourceNotFoundException("Content not found");
		}
		
		return Response.entity(content);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewContent(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "title") || 
			!ResourceUtil.isInt(formParams.getFirst("year")) ||
			!ResourceUtil.isInt(formParams.getFirst("length")))
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		Content content = new Content();
		
		content.setTitle(formParams.getFirst("title"));
		content.setYear(Integer.parseInt(formParams.getFirst("year")));
		content.setLength(Integer.parseInt(formParams.getFirst("length")));
		
		if (formParams.getFirst("actors") != null) {
			content.setActors(formParams.getFirst("actors"));
		}
		if (formParams.getFirst("director") != null) {
			content.setDirector(formParams.getFirst("director"));
		}
		if (formParams.getFirst("description") != null) {
			content.setDescription(formParams.getFirst("description"));
		}
		
		
		String tagName = null;
		int i = 0;
		while((tagName = formParams.getFirst("tags[" + i + "][name]")) != null) {
			Tag tag = tagDao.find(tagName);
			content.addTag(tag);
			i++;
		}
		
		contentService.insert(content);
		
		return Response.redirect(this, content.getId());
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	@Path("top")
	public Object createNewTopContent(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "title", "shortDescription") ||
			!ResourceUtil.isInt(formParams.getFirst("content"))) {
			throw new BadRequestException("You are missing some fields.");
		}
		
		TopContent topContent = new TopContent();
		
		topContent.setTitle(formParams.getFirst("title"));
		topContent.setShortDescription(formParams.getFirst("shortDescription"));
		
		Content content = contentService.find(Integer.parseInt(formParams.getFirst("content")));
		if (content == null) {
			throw new ResourceNotFoundException("Content not found.");
		}
		topContent.setContent(content);
		
		contentService.insert(topContent);
		
		return Response.success();
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Object updateContent(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Content content = contentService.find(id);
		if (content == null) {
			throw new ResourceNotFoundException("Content not found.");
		}
		
		if (formParams.getFirst("title") != null) {
			content.setTitle(formParams.getFirst("title"));
		}
		if (formParams.getFirst("actors") != null) {
			content.setActors(formParams.getFirst("actors"));
		}
		if (formParams.getFirst("director") != null) {
			content.setDirector(formParams.getFirst("director"));
		}
		if (formParams.getFirst("description") != null) {
			content.setDescription(formParams.getFirst("description"));
		}
		if (formParams.getFirst("year") != null) {
			content.setYear(Integer.parseInt(formParams.getFirst("year")));
		}
		if (formParams.getFirst("length") != null) {
			content.setLength(Integer.parseInt(formParams.getFirst("length")));
		}

		content.getTags().clear();
		
		String tagName = null;
		int i = 0;
		while((tagName = formParams.getFirst("tags[" + i + "][name]")) != null) {
			Tag tag = tagDao.find(tagName);
			content.addTag(tag);
			i++;
		}
		
		contentService.update(content);
		
		return Response.success();	
	}
		
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteContent(@PathParam("id") int id) 
	{
		Content content = contentService.find(id);
		if (content == null) {
			throw new ResourceNotFoundException("Content not found.");
		}
		
		contentService.delete(content);
		
		return Response.success();
	}
	
	@DELETE
	@Path("top/{id}")
	@Privilege("Delete")
	public Object deleteTopContent(@PathParam("id") int id) 
	{
		TopContent topContent = contentService.findTop(id);
		if (topContent == null) {
			throw new ResourceNotFoundException("Content not found.");
		}
		
		contentService.delete(topContent);
		
		return Response.success();
	}
	
	@GET
	@Path("{id}/rating")
	public Object getContentRating(@PathParam("id") int id) 
	{
		return Response.entity(contentMarkDao.getContentMark(id));
	}
	
	@POST
	@Path("{id}/rating")
	public Object setContentRating(@PathParam("id") int id, @QueryParam("mark") int mark) 
	{
		contentMarkDao.setContentMark(getCurrentUser().getId(), id, mark);
		return Response.success();
	}
	
	@GET
	@Path("{id}/projections")
	public Object getProjections(@PathParam("id") int id) 
	{
		return Response.entity(contentService.findContentProjections(id));
	}
	
}
