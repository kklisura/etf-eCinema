package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Type;


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
		
		Type type = new Type();
		type.setId(rs.getInt(7));
		content.setType(type);
		
		content.setFileId(rs.getString(8));
		content.setUpdatedAt(rs.getDate(9));
		content.setCreatedAt(rs.getDate(10));
		
		return content;
	}

}
