package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserActionContentDao;
import ba.etf.tim11.eCinema.models.UserActionContent;

public class UserActionContentDaoImpl implements UserActionContentDao
{
	private DaoFactory daoFactory;
	
	public UserActionContentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<UserActionContent> findAll() {
		
		List<UserActionContent> userActionContents = new ArrayList<UserActionContent>();
		Connection connection = daoFactory.getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActionContents");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				userActionContents.add((UserActionContent) map(resultSet));
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic): Something goes here.
		}
		return userActionContents;
	}

	@Override
	public UserActionContent find(int id) {
		UserActionContent userActionContent = null;
		Connection connection = daoFactory.getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActionContents WHERE id=?");
			ResultSet
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				userActionContent= (UserActionContent) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
	
		return userActionContent;
	}

	@Override
	public boolean insert(UserActionContent userActionContent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserActionContent userActionContent) {
		// TODO Auto-generated method stub
		return false;
	}

	// ------------------Helperi
	protected final Object map(ResultSet rs) {
		// TODO(nhuseinovic): Something goes here.
		return null;
	}
}
