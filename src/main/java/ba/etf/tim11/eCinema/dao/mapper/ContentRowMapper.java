package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Content;


public class ContentRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		Content content = new Content();
		
		content.setId(rs.getInt(1));
		content.setTitle(rs.getString(2));
		content.setActors(rs.getString(3));
		content.setDirector(rs.getString(4));
		content.setYear(rs.getInt(5));
		content.setLength(rs.getInt(6));
		content.setDescription(rs.getString(7));
		content.setUpdatedAt(rs.getTimestamp(8));
		content.setCreatedAt(rs.getTimestamp(9));
		
		return content;
	}
	
}
