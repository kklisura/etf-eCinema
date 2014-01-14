package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Comment;
import ba.etf.tim11.eCinema.models.UserAction;
import ba.etf.tim11.eCinema.models.UserActionComment;


public class UserActionCommentRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		UserActionComment userActionComment = new UserActionComment();
		
		userActionComment.setId(rs.getInt(1));
		
		Comment comment = new Comment();
		comment.setId(rs.getInt(2));
		userActionComment.setComment(comment);
		
		UserAction userAction = new UserAction();
		userAction.setId(rs.getInt(3));
		userActionComment.setUserAction(userAction);
		
		userActionComment.setUpdatedAt(rs.getDate(4));
		userActionComment.setCreatedAt(rs.getDate(5));
				
		return userActionComment;
	}
	
}
