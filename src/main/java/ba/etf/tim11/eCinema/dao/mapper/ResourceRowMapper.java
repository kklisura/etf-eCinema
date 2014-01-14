package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Resource;


public class ResourceRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		Resource resource = new Resource();
		
		resource.setId(rs.getInt(1));
		resource.setName(rs.getString(2));
		
		return resource;
	}
	
}
