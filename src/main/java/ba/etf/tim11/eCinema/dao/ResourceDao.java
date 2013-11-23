package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Resource;


public interface ResourceDao 
{
	public List<Resource> findAll() throws DaoException;
	
	public Resource find(int id) throws DaoException;
	
	public Resource find(String name) throws DaoException;
	
	public boolean insert(Resource resource) throws DaoException;
	
	public boolean update(Resource resource) throws DaoException;
	
	public boolean delete(Resource resource) throws DaoException;
}
