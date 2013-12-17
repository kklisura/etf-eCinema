package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Comment;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.User;


public class CommentRowMapper implements RowMapper 
{
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		Comment comment = new Comment();
		
		comment.setId(rs.getInt(1));
		comment.setTitle(rs.getString(2));
		comment.setText(rs.getString(3));
		
		Content content = new Content();
		content.setId(rs.getInt(4));
		comment.setContent(content);
		
		User user = new User();
		user.setId(rs.getInt(5));
		comment.setUser(user);
		
		comment.setUpdatedAt(rs.getDate(6));
		comment.setCreatedAt(rs.getDate(7));
						
		return comment;
	}

}
