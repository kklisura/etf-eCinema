package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.UserActionType;

public class UserActionTypeRowMapper implements RowMapper 
{
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		UserActionType  userActionType = new UserActionType(); 
		
		userActionType.setId(rs.getInt(1));
		userActionType.setType(rs.getString(2));
		
		return userActionType;
	}

}
