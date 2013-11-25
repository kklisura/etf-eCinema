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

import ba.etf.tim11.eCinema.dao.CommentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Comment;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;

@Path("comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource
{
	private DaoFactory daoFactory;
	private CommentDao commentDao;
	
	public CommentResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.commentDao = daoFactory.getCommentDao();
	}
	
	@GET
	@Privilege("List")
	public List<Comment> getAllComments() 
	{
		return commentDao.findAll();
	}
	
	@GET
	@Path("{id}")
	@Privilege("Read")
	public Comment getComment(@PathParam("id") int id) 
	{
		Comment comment = commentDao.find(id);
		
		if (comment == null) {
			throw new ResourceNotFoundException("Comment not found");
		}
		
		return comment;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Comment createNewComment(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "title", "text","content","user") || 
			!ResourceUtil.isInt(formParams.getFirst("content")) ||
			!ResourceUtil.isInt(formParams.getFirst("user")))
		{
			throw new BadRequestException("You are missing title and/or text field.");
		}
		
		Comment comment = new Comment();
		
		comment.setTitle(formParams.getFirst("title"));
		comment.setText(formParams.getFirst("tex"));
		
		Content content = new Content();
		content.setId(Integer.parseInt(formParams.getFirst("content")));
		comment.setContent(content);
		
		User user = new User();
		user.setId(Integer.parseInt(formParams.getFirst("user")));
		comment.setUser(user);

		commentDao.insert(comment);
		
		return comment;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateComment(@PathParam("id") int id, MultivaluedMap<String, String> formParams) 
	{	
		Comment comment = commentDao.find(id);
		if (comment == null) {
			throw new ResourceNotFoundException("Comment not found.");
		}
		
		if (formParams.getFirst("title") != null)
			comment.setTitle(formParams.getFirst("title"));
		
		if (formParams.getFirst("text") != null)
			comment.setText(formParams.getFirst("text"));
		if (formParams.getFirst("content") != null)
		{
			Content content = new Content();
			content.setId(Integer.parseInt(formParams.getFirst("content")));
			comment.setContent(content);
		}
		
		if (formParams.getFirst("user") != null)
		{
			User user = new User();
			user.setId(Integer.parseInt(formParams.getFirst("user")));
			comment.setUser(user);
		}

		commentDao.update(comment);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteComment(@PathParam("id") int id) 
	{
		Comment comment = commentDao.find(id);
		if (comment == null) {
			throw new ResourceNotFoundException("Comment not found.");
		}
		
		commentDao.delete(comment);
		
		return Response.success();
	}
	

}
