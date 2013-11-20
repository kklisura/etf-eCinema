package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.dao.mapper.PrivilegeRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Privilege;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class PrivilegeDaoImpl implements PrivilegeDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new PrivilegeRowMapper();
	
	
	public PrivilegeDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Privilege> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Privileges", rowMapper);
	}

	@Override
	public Privilege find(int id) throws DaoException 
	{		
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Privileges WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);	
	}

	@Override
	public boolean insert(Privilege privilege) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		
		try 
		{
			String query = "INSERT INTO Privileges (allow, resources_id, roles_id, privilegetypes_id) VALUES (?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setBoolean(1, privilege.isAllowed());
			preparedStatement.setInt(2,privilege.getResource().getId());
			preparedStatement.setInt(3, privilege.getRole().getId());
			preparedStatement.setInt(4, privilege.getPrivilegeType().getId());

			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating privilege failed, no rows affected.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	
	        	privilege.setId(generatedKeys.getInt(1));
	        } else {
	            throw new SQLException("Creating privilege failed, no generated key obtained.");
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
	public boolean update(Privilege user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		
		try
		{
			String query = "UPDATE Privilege SET comment = ?, nesto = ? WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			// preparedStatement.setInt(1,  comment.getComment());
			// ..
			// ..
			// ..
			// preparedStatement.setInt(10,  comment.getId());
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating Privilege failed, no rows affected.");
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
