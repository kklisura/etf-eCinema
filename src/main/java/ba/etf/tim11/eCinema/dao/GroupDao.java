package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Group;


public interface GroupDao 
{
	public List<Group> findAll() throws DaoException;
	
	public Group find(int id) throws DaoException;
	
	public Group findByName(String name) throws DaoException;
	
	public boolean insert(Group group) throws DaoException;
	
	public boolean update(Group group) throws DaoException;
}
