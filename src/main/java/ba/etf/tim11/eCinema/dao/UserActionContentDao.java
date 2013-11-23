package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.UserActionContent;


public interface UserActionContentDao
{
	public List<UserActionContent> findAll() throws DaoException;
	
	public UserActionContent find(int id) throws DaoException;
	
	public boolean insert(UserActionContent userActionContent) throws DaoException;
	
	public boolean update(UserActionContent userActionContent) throws DaoException;
	
	public boolean delete(UserActionContent userActionContent) throws DaoException;
}