package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Role;


public interface RoleDao
{
	public List<Role> findAll() throws DaoException;
	
	public Role find(int id) throws DaoException;
	
	public Role findByName(String name) throws DaoException;
	
	public boolean insert(Role role) throws DaoException;
	
	public boolean update(Role role) throws DaoException;
}
