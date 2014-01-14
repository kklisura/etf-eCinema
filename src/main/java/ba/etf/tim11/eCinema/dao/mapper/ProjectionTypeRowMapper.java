package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.ProjectionType;


public class ProjectionTypeRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		ProjectionType projectionType = new ProjectionType();
		
		projectionType.setId(rs.getInt(1));
		projectionType.setType(rs.getString(2));
		projectionType.setUpdatedAt(rs.getDate(3));
		projectionType.setCreatedAt(rs.getDate(4));
		
		return projectionType;
	}
	
}
