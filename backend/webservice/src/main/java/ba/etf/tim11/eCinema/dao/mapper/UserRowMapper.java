package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.State;
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
		user.setUsername(rs.getNString(4));
		user.setEmail(rs.getNString(5));
		user.setPhone(rs.getNString(6));
		user.setAddress(rs.getNString(7));
		user.setDateOfBirth(rs.getDate(8));
		user.setPlaceOfBirth(rs.getString(9));
		
		State state = new State();
		state.setId(rs.getInt(10));
		user.setState(state);
		
		user.setPassword(rs.getString(11));
		user.setSalt(rs.getInt(12));
		user.setUpdatedAt(rs.getDate(13));
		user.setCreatedAt(rs.getDate(14));
		
		return user;
	}

}
