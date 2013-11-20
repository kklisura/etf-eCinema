package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserActionContentDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserActionContentRowMapper;
import ba.etf.tim11.eCinema.models.UserActionContent;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class UserActionContentDaoImpl implements UserActionContentDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserActionContentRowMapper();
	
	
	public UserActionContentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<UserActionContent> findAll() 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM UserActionContents", rowMapper);
	}

	@Override
	public UserActionContent find(int id) 
	{
		Connection connection = daoFactory.getConnection();

		String query = "SELECT * FROM UserActionContent WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public boolean insert(UserActionContent userActionContent)
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		
		try 
		{
			String query = "INSERT INTO UserActionContents (..) VALUES (?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			// preparedStatement.setInt(1,  content.get..());
			// ..
			// ..
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating UserActionContent failed, no rows affected.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	// TODO(kklisura): Fill comment id here.
	            // content.setId(generatedKeys.getLong(1));
	        } else {
	            throw new SQLException("Creating UserActionContent failed, no generated key obtained.");
	        }
	        
		} catch (SQLException e) 
		{
			throw new DaoException("insert failed. " + e.getMessage());
		} finally
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
				
				if (generatedKeys != null)
					generatedKeys.close();
			} catch (SQLException e) 
			{
				// TOOD(kklisura): Better handling of this error.
				e.printStackTrace();
				throw new DaoException("Something went wrong " + e.getMessage());
			}
		}
		
		return true;
	}

	@Override
	public boolean update(UserActionContent userActionContent) 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		
		try
		{
			String query = "UPDATE Comment SET comment = ?, nesto = ? WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			// preparedStatement.setInt(1,  comment.getComment());
			// ..
			// ..
			// ..
			// preparedStatement.setInt(10,  comment.getId());
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating comment failed, no rows affected.");
	        }
	        
		} catch (SQLException e) {
			throw new DaoException("executeSelectMultipleQuery failed. " + e.getMessage());
		} finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) 
			{
				// TOOD(kklisura): Better handling of this error.
				e.printStackTrace();
				throw new DaoException("Something went wrong " + e.getMessage());
			}
		}
		
		return true;
	}
	
}
