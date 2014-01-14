package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Group;


public interface GroupDao 
{
	public List<Group> findAll(int offset, int limit) throws DaoException;
	
	public List<Group> findAllByUser(int userId) throws DaoException;
	
	public Group find(int id) throws DaoException;
	
	public Group find(String name) throws DaoException;
	
	public boolean insert(Group group) throws DaoException;
	
	public boolean update(Group group) throws DaoException;
	
	public boolean delete(Group group) throws DaoException;

}
