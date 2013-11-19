package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.models.User;


public class UserDaoImpl extends BaseDao implements UserDao 
{	
	@Override
	public List<User> findAll() 
	{
		List<User> users = new ArrayList<User>();
		
		Connection connection = getConnection();
		
	    try 
	    {
	        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users");
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
            while(resultSet.next()) {
                users.add((User) map(resultSet));
            }

	    } catch (SQLException e ) 
	    {
	    	// NOTE(): Something goes here.

	    } finally {
	    	// NOTE(): Something gors here.

	    }
	    
		return users;
	}

	@Override
	public User find(int id) 
	{
		User user = null;
		
		Connection connection = getConnection();
		
	    try 
	    {
	        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
	        
	        preparedStatement.setInt(1, id);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
            if (resultSet.next()) {
                user = (User) map(resultSet);
            }

	    } catch (SQLException e ) 
	    {
	    	// NOTE(): Something goes here.

	    } finally {
	    	// NOTE(): Something gors here.

	    }
	    
		return user;
	}

	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	// Helper ---------------------------------------------------------------
	
	protected final Object map(ResultSet rs)
	{
		// TODO(kklisura): Need to implement this.
		//
		// User user = new User();
		// user.setId(rs.getInt(1));
		// user.setFirstName(rs.getString(2));
		// ...
		// return user;
		// 
		return null;
	}

}
