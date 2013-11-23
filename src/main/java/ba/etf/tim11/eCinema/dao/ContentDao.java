package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Content;


public interface ContentDao 
{
	public List<Content> findAll() throws DaoException;
	
	public Content find(int id) throws DaoException;
	
	public boolean insert(Content content) throws DaoException;
	
	public boolean update(Content content) throws DaoException;
	
	public boolean delete(Content content) throws DaoException;
}
