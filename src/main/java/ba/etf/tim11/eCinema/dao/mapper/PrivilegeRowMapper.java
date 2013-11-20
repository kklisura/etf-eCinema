package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Privilege;

public class PrivilegeRowMapper implements RowMapper {

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Privilege privilege = new Privilege();
		
		privilege.setId(rs.getInt(1));		
		privilege.setIsAllowed(rs.getBoolean(2));
		
		// TODO(nhuseinovic): Need to add resources,roles,privilegeTypes
		
		
		return privilege;
	}

}
