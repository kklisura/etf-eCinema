package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.mapper.GroupRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class GroupDaoImpl  implements GroupDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new GroupRowMapper();
	
	
	public GroupDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Group> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Groups", rowMapper);
	}

	@Override
	public Group find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Groups WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}
	
	@Override
	public Group findByName(String name) throws DaoException {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		return null;
	}

	@Override
	public boolean insert(Group group) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		
		try 
		{
			String query = "INSERT INTO Groups (name, description) VALUES (?, ?)";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			
			preparedStatement.setString(1, group.getName());
			preparedStatement.setString(2, group.getDescription());
			
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating group failed, no rows affected.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	
	            group.setId(generatedKeys.getInt(1));
	        } else {
	            throw new SQLException("Creating group failed, no generated key obtained.");
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
	public boolean update(Group group) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		
		try
		{
			String query = "UPDATE Group SET comment = ?, nesto = ? WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			// preparedStatement.setInt(1,  comment.getComment());
			// ..
			// ..
			// ..
			// preparedStatement.setInt(10,  comment.getId());
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating Group failed, no rows affected.");
	        }
	        
		} catch (SQLException e) {
			throw new DaoException("Update failed. " + e.getMessage());
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
