package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.ContentMark;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.UserAction;


public class ContentMarkRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		ContentMark contentMark = new ContentMark();
		
		contentMark.setId(rs.getInt(1));
		contentMark.setMark(rs.getInt(2));

		Content content = new Content();
		content.setId(rs.getInt(3));
		contentMark.setContent(content);
		
		UserAction userAction = new UserAction();
		userAction.setId(rs.getInt(4));
		contentMark.setUserAction(userAction);
		
		contentMark.setUpdatedAt(rs.getDate(5));
		contentMark.setCreatedAt(rs.getDate(6));
		
		return contentMark;
	}
	
}

