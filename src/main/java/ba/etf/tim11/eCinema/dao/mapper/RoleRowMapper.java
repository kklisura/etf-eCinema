package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Role;


public class RoleRowMapper implements RowMapper
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Role role = new Role();
		
		role.setId(rs.getInt(1));
		role.setName(rs.getString(2));
		role.setDescription(rs.getString(3));
		
		return role;
	}
	
}
