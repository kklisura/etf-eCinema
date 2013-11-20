package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.User;

public class UserRowMapper implements RowMapper 
{
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{	
		User user = new User();
		
		user.setId(rs.getInt(1));
		user.setLastName(rs.getString(2));
		user.setFirstName(rs.getNString(3));
		user.setEmail(rs.getNString(4));
		user.setPhone(rs.getNString(5));
		user.setAddress(rs.getNString(6));
		user.setDateOfBirth(rs.getDate(7));
		user.setPlaceOfBirth(rs.getString(8));
		//user.setState(....);
		user.setPassword(rs.getString(10));
		user.setSalt(rs.getInt(11));
		user.setUpdatedAt(rs.getDate(12));
		user.setCreatedAt(rs.getDate(13));
		
		return user;
	}

}
