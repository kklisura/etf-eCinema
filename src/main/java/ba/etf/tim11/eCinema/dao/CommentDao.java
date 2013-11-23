package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Comment;


public interface CommentDao
{
	public List<Comment> findAll() throws DaoException;
	
	public Comment find(int id) throws DaoException;
	
	public boolean insert(Comment comment) throws DaoException;
	
	public boolean update(Comment comment) throws DaoException;
	
	public boolean delete(Comment comment) throws DaoException;
}
