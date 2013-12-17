package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection; 
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
	public List<AudioSynchronization> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM AudioSynchronizations LIMIT ?, ?", 
									offset, 
									limit);
	}
	
	@Override
	public List<AudioSynchronization> findAllByContent(int contentId, int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM AudioSynchronizations WHERE contents_id = ? LIMIT ?, ?",
									contentId,
									offset,
									limit);
	}

	@Override
	public AudioSynchronization find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
										     rowMapper,
											 "SELECT * FROM AudioSynchronizations WHERE id = ?",
											 id);
	}

	@Override
	public boolean insert(AudioSynchronization audioSynchronization) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
										  "INSERT INTO AudioSynchronizations (contents_id, languages_id, fileId) VALUES (?, ?, ?)",
										  audioSynchronization.getContent().getId(),
										  audioSynchronization.getLanguage().getId(),
										  audioSynchronization.getFileId());
		
		audioSynchronization.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(AudioSynchronization audioSynchronization) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,
							  "UPDATE AudioSynchronizations SET contents_id = ?, languages_id = ?, fileId = ? WHERE id = ?",
							  audioSynchronization.getContent().getId(),
							  audioSynchronization.getLanguage().getId(),
							  audioSynchronization.getFileId(),
							  audioSynchronization.getId());
		
		return true;
	}


	@Override
	public boolean delete(AudioSynchronization audioSynchronization) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM AudioSynchronizations WHERE id = ?", audioSynchronization.getId());
		
		return true;	
	}
		
}
