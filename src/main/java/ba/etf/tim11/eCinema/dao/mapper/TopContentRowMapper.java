package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.TopContent;


public class TopContentRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		TopContent topContent = new TopContent();
		
		topContent.setId(rs.getInt(1));
		topContent.setTitle(rs.getString(2));
		topContent.setShortDescription(rs.getString(3));
		
		Content content = new Content();
		content.setId(rs.getInt(4));
		topContent.setContent(content);
		
		topContent.setUpdatedAt(rs.getDate(5));
		topContent.setCreatedAt(rs.getDate(6));
		
		return topContent;
	}
	
}

