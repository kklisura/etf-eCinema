package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.UserAction;
import ba.etf.tim11.eCinema.models.UserActionContent;


public class UserActionContentRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		UserActionContent userActionContent = new UserActionContent();
		
		userActionContent.setId(rs.getInt(1));
		
		Content content = new Content();
		content.setId(rs.getInt(2));
		userActionContent.setContent(content);
		
		UserAction userAction = new UserAction();
		userAction.setId(rs.getInt(3));
		userActionContent.setUserAction(userAction);
			
		userActionContent.setUpdatedAt(rs.getDate(4));
		userActionContent.setCreatedAt(rs.getDate(5));
		
		return userActionContent;
	}

}
