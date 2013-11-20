package ba.etf.tim11.eCinema.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;


public final class DaoUtil 
{
	@SuppressWarnings("unchecked")
	public static <T> List<T> executeSelectMultipleQuery(Connection connection, String query, RowMapper rowMapper) throws DaoException
	{
		List<T> result = new ArrayList<T>();
		
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
		    ResultSet resultSet = preparedStatement.executeQuery();
		        
	        while(resultSet.next()) {
	        	result.add((T) rowMapper.map(resultSet));
	        }
	        
		} catch (SQLException e) 
		{
			throw new DaoException("executeSelectMultipleQuery failed. " + e.getMessage());
		}
		
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T executeSelectWithId(Connection connection, String query, int id, RowMapper rowMapper) throws DaoException
	{
		T result = null;
		
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, id);
			
		    ResultSet resultSet = preparedStatement.executeQuery();
		        
	        if (resultSet.next()) {
	        	result = (T) rowMapper.map(resultSet);
	        }
	        
		} catch (SQLException e) 
		{
			throw new DaoException("executeSelectWithId failed. " + e.getMessage());
		}
		
		return result;
	}
	
}
