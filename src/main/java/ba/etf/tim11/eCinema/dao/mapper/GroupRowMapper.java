package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Group;


public class GroupRowMapper implements RowMapper {

	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		Group group = new Group();
		
		group.setId(rs.getInt(1));
		
		group.setName(rs.getString(2));
		
		group.setDescription(rs.getString(3));
		
		group.setUpdatedAt(rs.getDate(4));
		
		group.setCreatedAt(rs.getDate(5));
		
		return null;
	}

}
