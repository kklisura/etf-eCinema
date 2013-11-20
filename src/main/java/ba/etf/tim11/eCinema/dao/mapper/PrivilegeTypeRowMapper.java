package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.PrivilegeType;

public class PrivilegeTypeRowMapper implements RowMapper {

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		PrivilegeType privilegeType = new PrivilegeType();
		
		privilegeType.setId(rs.getInt(1));		
		privilegeType.setName(rs.getString(2));
		
		return privilegeType;
	}

}
