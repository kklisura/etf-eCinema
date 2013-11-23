package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.UserActionComment;


public interface UserActionCommentDao 
{	
	public List<UserActionComment> findAll() throws DaoException;
	
	public UserActionComment find(int id) throws DaoException;
	
	public boolean insert(UserActionComment userActionComment) throws DaoException;
	
	public boolean update(UserActionComment userActionComment) throws DaoException;
	
	public boolean delete(UserActionComment userActionComment) throws DaoException;
}
