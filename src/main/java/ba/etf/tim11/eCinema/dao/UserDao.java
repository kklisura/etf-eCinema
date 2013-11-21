package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.User;


public interface UserDao
{
	public List<User> findAll() throws DaoException;
	
	public User find(int id) throws DaoException;
	
	public User find(String username) throws DaoException;
	
	public User insert(User user) throws DaoException;
	
	public boolean update(User user) throws DaoException;
	
	public boolean delete(User user) throws DaoException;
}

