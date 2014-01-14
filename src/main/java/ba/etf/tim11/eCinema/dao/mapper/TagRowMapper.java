package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Tag;


public class TagRowMapper implements RowMapper
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Tag tag = new Tag();
		
		tag.setId(rs.getInt(1));
		tag.setName(rs.getString(2));
		tag.setUpdatedAt(rs.getDate(3));
		tag.setCreatedAt(rs.getDate(4));
		
		return tag;
	}
	
}
