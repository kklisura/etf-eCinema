package ba.etf.tim11.eCinema.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
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
	
	
	public static DateFormat dateFormat = null;
	
	public static java.util.Date string2Date(String date) throws ParseException
	{
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		}
		
		return dateFormat.parse(date);
	}
	
	
	public static java.sql.Date utilDate2SqlDatw(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	
	// http://stackoverflow.com/questions/4895523/java-string-to-sha1
	public static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
}
