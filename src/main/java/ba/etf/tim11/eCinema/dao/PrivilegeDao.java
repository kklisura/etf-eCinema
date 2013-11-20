package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Privilege;


public interface PrivilegeDao
{
    public List<Privilege> findAll() throws DaoException;
	
	public Privilege find(int id) throws DaoException;
	
	public boolean insert(Privilege user) throws DaoException;
	
	public boolean update(Privilege user) throws DaoException;
}
