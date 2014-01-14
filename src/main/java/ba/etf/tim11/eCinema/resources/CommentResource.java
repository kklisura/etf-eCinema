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
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Comment;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.service.LoginService;
import ba.etf.tim11.eCinema.service.ServiceFactory;
import ba.etf.tim11.eCinema.service.impl.ServiceFactoryImpl;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource extends BaseResource
{
	private static ServiceFactory serviceFactory;
	private static LoginService loginService;
	
	private DaoFactory daoFactory;
	private ContentDao contentDao;
	private CommentDao commentDao;
	private UserDao userDao;
	
	
	public CommentResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		
		this.commentDao = daoFactory.getCommentDao();
		this.contentDao = daoFactory.getContentDao();
		this.userDao = daoFactory.getUserDao();
		
		this.serviceFactory = ServiceFactoryImpl.getInstance();
		this.loginService = serviceFactory.getLoginService();
	}
	
	
	@GET
	@Path("{content_id}")
	@Privilege("List")
	public Object getContentComments(@PathParam("content_id") int contentId) 
	{
		if (contentDao.find(contentId) == null) {
			throw new ResourceNotFoundException("Content is unknown.");
		}
		
		List<Comment> comments = commentDao.findAllByContent(contentId, offset, limit);
		for(Comment comment : comments) {
			comment.setUser(userDao.find(comment.getUser().getId()));
		}
		
		return Response.paginated(comments, offset, limit, -1);
	}
	
	@POST
	@Path("{content_id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Object createNewComment(@PathParam("content_id") int contentId, MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "text")) {
			throw new BadRequestException("You are missing text fields.");
		}
		
		Content content = contentDao.find(contentId);
		if (content == null) {
			throw new ResourceNotFoundException("Content is unknown.");
		}
		
		User user = getCurrentUser();
		
		Comment comment = new Comment();
		
		comment.setText(formParams.getFirst("text"));
		comment.setContent(content);
		comment.setUser(user);

		commentDao.insert(comment);
		
		return Response.entity(comment);
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Object deleteComment(@PathParam("id") int id) 
	{
		Comment comment = commentDao.find(id);
		if (comment == null) {
			throw new ResourceNotFoundException("Comment not found.");
		}
		
		// TODO(kklisura): Check if user made this comment.
		
		commentDao.delete(comment);
		
		return Response.success();
	}
	
}
