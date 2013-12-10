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
		User user = new User();
		UserActionType userActionType = new UserActionType(); 
		
		userAction.setId(rs.getInt(1));
		user.setId(rs.getInt(2));
		userActionType.setId(rs.getInt(3));
		userAction.setUpdatedAt(rs.getDate(4));
		userAction.setCreatedAt(rs.getDate(5));
		
		return userAction;
	}

}
