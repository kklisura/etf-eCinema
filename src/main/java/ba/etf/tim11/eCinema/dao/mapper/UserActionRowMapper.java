package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.models.UserAction;
import ba.etf.tim11.eCinema.models.UserActionType;


public class UserActionRowMapper implements RowMapper
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		UserAction userAction = new UserAction();
		
		userAction.setId(rs.getInt(1));
		
		User user = new User();
		user.setId(rs.getInt(2));
		userAction.setUser(user);
		
		UserActionType userActionType = new UserActionType(); 
		userActionType.setId(rs.getInt(3));
		userAction.setUserActionType(userActionType);
		
		userAction.setTime(rs.getTimestamp(4));
		userAction.setUpdatedAt(rs.getTimestamp(5));
		userAction.setCreatedAt(rs.getTimestamp(6));
		
		return userAction;
	}
	
}
