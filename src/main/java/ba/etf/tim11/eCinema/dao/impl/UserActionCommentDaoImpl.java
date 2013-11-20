package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserActionCommentDao;
import ba.etf.tim11.eCinema.models.UserActionComment;


public class UserActionCommentDaoImpl implements UserActionCommentDao
{
	private DaoFactory daoFactory;
	
	public UserActionCommentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<UserActionComment> findAll()
	{
		List<UserActionComment> userActionComments = new ArrayList<UserActionComment>();
		Connection connection = daoFactory.getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActionComments");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				userActionComments.add((UserActionComment) map(resultSet));
			}	
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
		return userActionComments;
	}

	@Override
	public UserActionComment find(int id) {
		UserActionComment userActionComment=null;
		Connection connection = daoFactory.getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserActionComments WHERE id=?");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				userActionComment = (UserActionComment) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally
		{
			// TODO(nhuseinovic): Something goes here.
		}
		return userActionComment;
	}

	@Override
	public boolean insert(UserActionComment userActionComment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserActionComment userActionComment) {
		// TODO Auto-generated method stub
		return false;
	}

	// -----------------Helper
	
	protected final Object map(ResultSet rs) {
		
		// TODO(nhuseinovic): Something goes here.
		
		return null;
	}

}
