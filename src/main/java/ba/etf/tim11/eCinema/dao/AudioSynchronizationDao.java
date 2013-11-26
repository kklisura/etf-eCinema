package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.AudioSynchronization;


public interface AudioSynchronizationDao
{
	public List<AudioSynchronization> findAll() throws DaoException;
	
	public List<AudioSynchronization> findAllBy(int contentId, String language) throws DaoException;
	
	public AudioSynchronization find(int id) throws DaoException;
		
	public boolean insert(AudioSynchronization audioSynchronization) throws DaoException;
		
	public boolean update(AudioSynchronization audioSynchronization) throws DaoException;
	
	public boolean delete(AudioSynchronization audioSynchronization) throws DaoException;

}
