package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.UserActionTypeDao;
import ba.etf.tim11.eCinema.models.UserActionType;


public class UserActionTypeDaoImpl extends BaseDao implements UserActionTypeDao 
{
	@Override
	public List<UserActionType> findAll() {
		
		List<UserActionType> userActionTypes = new ArrayList<UserActionType>();
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActionTypes");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				userActionTypes.add((UserActionType) map(resultSet));
			}
		
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally
		{
			// TODO(nhuseinovic): Something goes here.
		}
		return userActionTypes;
	}

	@Override
	public UserActionType find(int id) {
		
		UserActionType userActionType = null;
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActionTypes WHERE id=?");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				userActionType = (UserActionType) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
		
		return userActionType;
	}

	@Override
	public boolean insert(UserActionType userUserActionType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserActionType user) {
		// TODO Auto-generated method stub
		return false;
	}

//-------------------- Helperi
	protected final Object map(ResultSet rs) {
		
		// TODO(nhuseinovic): Something goes here.
		
		return null;
	}

}
