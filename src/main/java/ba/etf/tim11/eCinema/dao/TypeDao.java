package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Type;

public interface TypeDao
{
	public List<Type> findAll() throws DaoException;
	
	public Type find(int id) throws DaoException;
	
	public boolean insert (Type type) throws DaoException;
	
	public boolean update (Type type) throws DaoException;

}
