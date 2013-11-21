package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserRowMapper;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class UserDaoImpl implements UserDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserRowMapper();
	
	
	public UserDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<User> findAll() throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Users", rowMapper);
	}	

	@Override
	public User find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Users WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}
	
	@Override
	public User find(String username) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		User user = null;
		
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
			
			preparedStatement.setString(1, username);
			
		    ResultSet resultSet = preparedStatement.executeQuery();
		        
	        if (resultSet.next()) {
	        	user = (User) rowMapper.map(resultSet);
	        }
	        
		} catch (SQLException e) 
		{
			throw new DaoException("find(byUsername) failed. " + e.getMessage());
		}
		
		return user;
	}


	@Override
	public User insert(User user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		
		try 
		{	
			String query = "INSERT INTO Users (lastName, firstName, username, email, phone, address, dateOfBirth, placeOfBirth, states_id, password, salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, user.getLastName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setDate(7, (Date) user.getDateOfBirth());
			preparedStatement.setString(8, user.getPlaceOfBirth());
			preparedStatement.setInt(9, user.getState().getId());
			preparedStatement.setString(10, user.getPassword());
			preparedStatement.setInt(11, user.getSalt());			
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating User failed, no rows affected.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	user.setId(generatedKeys.getInt(1));
	        } else {
	            throw new SQLException("Creating User failed, no generated key obtained.");
	        }
		
		} catch (SQLException e) {
			throw new DaoException("Insert failed. " + e.getMessage());
		}finally 
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
		
		return user;
	}

	@Override
	public boolean update(User user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		
		try 
		{	
			String query = "UPDATE Users SET lastName = ?, firstName = ?, username = ?, email = ?, phone = ?, adress = ?, dateOfBirth = ?, placeOfBirth = ?, states_id = ?, password = ?, salt = ? WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, user.getLastName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setDate(7, (Date) user.getDateOfBirth());
			preparedStatement.setString(8, user.getPlaceOfBirth());
			preparedStatement.setInt(9, user.getState().getId());
			preparedStatement.setString(10, user.getPassword());
			preparedStatement.setInt(11, user.getSalt());			
			
			preparedStatement.setInt(12, user.getId());	
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Updating User failed, no rows affected.");
	        }
		
		} catch (SQLException e) {
			throw new DaoException("Update failed. " + e.getMessage());
		}finally 
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
	
	@Override
	public boolean delete(User user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		
		try 
		{		
			preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
			
			preparedStatement.setInt(1, user.getId());			
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating User failed, no rows affected.");
	        }
	        
		} catch (SQLException e) {
			throw new DaoException("Delete failed. " + e.getMessage());
		}finally 
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
