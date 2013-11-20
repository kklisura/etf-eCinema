package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;

import ba.etf.tim11.eCinema.models.Comment;


public class CommentRowMapper implements RowMapper 
{
	@Override
	public Object map(ResultSet rs) 
	{
		Comment comment = new Comment();
		// TODO(kklisura): Fill comment with ResultSet data here.
		// 
		// ..
		// comment.setId(rs.getInt(1));
		// ..
		// 
		return comment;
	}

}
