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
		user.setFirstName(rs.getString(3));
		user.setUsername(rs.getString(4));
		user.setEmail(rs.getString(5));
		user.setPhone(rs.getString(6));;
		user.setDateOfBirth(rs.getDate(7));
		
		State state = new State();
		state.setId(rs.getInt(8));
		user.setState(state);
		
		user.setPassword(rs.getString(9));
		user.setSalt(rs.getInt(10));
		user.setUpdatedAt(rs.getTimestamp(11));
		user.setCreatedAt(rs.getTimestamp(12));
		
		return user;
	}
	
}
