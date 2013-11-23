package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import ba.etf.tim11.eCinema.dao.AudioSynchronizationDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.AudioSynchronizationRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.AudioSynchronization;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class AudioSynchronizationDaoImpl implements AudioSynchronizationDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new AudioSynchronizationRowMapper();
	
	public AudioSynchronizationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<AudioSynchronization> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM AudioSynchronizations", rowMapper);
	}

	@Override
	public AudioSynchronization find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		String query = "SELECT * FROM AudioSynchronizations WHERE id=?";
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public boolean insert(AudioSynchronization audioSynchronization) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		
		try 
		{
			String query = "INSERT INTO AudioSynchronizations (contents_id, languages_id, fileId) VALUES (?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, audioSynchronization.getContent().getId());
			preparedStatement.setInt(2, audioSynchronization.getLanguage().getId());
			preparedStatement.setString(3, audioSynchronization.getFileId());
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating audioSynchronization failed, no rows affected.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        
	        	audioSynchronization.setId(generatedKeys.getInt(1));
	       
	        } else {
	            throw new SQLException("Creating audioSynchronization failed, no generated key obtained.");
	        }
	        
		} catch (SQLException e) 
		{
			throw new DaoException("executeSelectMultipleQuery failed. " + e.getMessage());
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
				// TOOD(nhuseinovc): Better handling of this error.
				e.printStackTrace();
				throw new DaoException("Something went wrong " + e.getMessage());
			}
		}
		
		return true;
	}

	@Override
	public boolean update(AudioSynchronization audioSynchronization) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		PreparedStatement preparedStatement = null;
		
		try 
		{	
			String query = "UPDATE AudioSynchronizations SET contents_id = ?, languages_id = ?, fileId = ? WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1,audioSynchronization.getContent().getId());
			preparedStatement.setInt(2, audioSynchronization.getLanguage().getId());
			preparedStatement.setString(3, audioSynchronization.getFileId());
						
			preparedStatement.setInt(4, audioSynchronization.getId());	
			
			int affectedRows = preparedStatement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Updating AudioSynchronization failed, no rows affected.");
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
				// TOOD(nhuseinovc): Better handling of this error.
				e.printStackTrace();
				throw new DaoException("Something went wrong " + e.getMessage());
			}
		}
		
		return true;
	}

}
