package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.TopContent;


public interface TopContentDao
{
	public List<TopContent> findAll(int offset, int limit) throws DaoException;
	
	public TopContent find(int id) throws DaoException;
	
	public boolean insert (TopContent topContent) throws DaoException;
	
	public boolean update (TopContent topContent) throws DaoException;
	
	public boolean delete (TopContent topContent) throws DaoException;

}
