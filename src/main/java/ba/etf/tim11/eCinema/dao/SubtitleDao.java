package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Subtitle;

public interface SubtitleDao
{
	public List<Subtitle> findAll() throws DaoException;
	
	public Subtitle find(int id) throws DaoException;
	
	public boolean insert (Subtitle subtitle) throws DaoException;
	
	public boolean update (Subtitle subtitle) throws DaoException;
	
	public boolean delete (Subtitle subtitle) throws DaoException;	
	

}
