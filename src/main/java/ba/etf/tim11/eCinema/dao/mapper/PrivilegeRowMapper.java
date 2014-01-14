package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Privilege;
import ba.etf.tim11.eCinema.models.Resource;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.models.PrivilegeType;;


public class PrivilegeRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Privilege privilege = new Privilege();
		
		privilege.setId(rs.getInt(1));		
		privilege.setIsAllowed(rs.getBoolean(2));
		
		Resource resource = new Resource ();
		resource.setId(rs.getInt(3));
		privilege.setResource(resource);
		
		Role role = new Role();
		role.setId(4);
		privilege.setRole(role);
		
		PrivilegeType privilegeType = new PrivilegeType();
		privilegeType.setId(rs.getInt(5));
		privilege.setPrivilegeType(privilegeType);
		
		return privilege;
	}
	
}
