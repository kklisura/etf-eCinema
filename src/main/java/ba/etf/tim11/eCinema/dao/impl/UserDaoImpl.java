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
	public boolean insert(User user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		
		
		try {
			
			String query = "INSERT INTO Users (lastName, firstName, email, phone, adress, dateOfBirth, placeOfBirth, states_id, password, salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, user.getLastName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setDate(6, (Date) user.getDateOfBirth());
			preparedStatement.setString(7, user.getPlaceOfBirth());
			preparedStatement.setInt(8, user.getState().getId());
			preparedStatement.setString(9, user.getPassword());
			preparedStatement.setInt(10, user.getSalt());
			// userpreferences
			
			
		
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating comment failed, no rows affected.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	user.setId(generatedKeys.getInt(1));
	        } else {
	            throw new SQLException("Creating comment failed, no generated key obtained.");
	        }
		
		} catch (SQLException e) {
			throw new DaoException("executeSelectMultipleQuery failed. " + e.getMessage());
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
		return true;
	}

	@Override
	public boolean update(User user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
