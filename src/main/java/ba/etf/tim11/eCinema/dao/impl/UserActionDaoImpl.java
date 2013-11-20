package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.UserActionDao;
import ba.etf.tim11.eCinema.models.UserAction;

public class UserActionDaoImpl extends BaseDao implements UserActionDao
{

	@Override
	public List<UserAction> findAll() {
		
		List<UserAction> userActions = new ArrayList<UserAction>();
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActions");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				userActions.add((UserAction) map(resultSet));
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
		return userActions;
	}

	@Override
	public UserAction find(int id) {
		
		UserAction userAction = null;
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActions WHERE id=?");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				userAction = (UserAction) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
		return userAction;
	}

	@Override
	public boolean insert(UserAction userAction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserAction userAction) {
		// TODO Auto-generated method stub
		return false;
	}

	
	protected final Object map(ResultSet rs) {
		// TODO(nhuseinovic): Something goes here.
		
		return null;
	}

}
