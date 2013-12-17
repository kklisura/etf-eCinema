package ba.etf.tim11.eCinema.dao;

import ba.etf.tim11.eCinema.models.Session;


public interface SessionDao 
{
	public Session find(String key) throws DaoException;
	
	public boolean insert(Session session) throws DaoException;
	
	public boolean delete(Session session) throws DaoException;
}