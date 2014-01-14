package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Role;


public interface RoleDao
{
	public List<Role> findAll(int offset, int limit) throws DaoException;
	
	public List<Role> findAllByUser(int userId) throws DaoException;
	
	public List<Role> findAllByGroup(int groupId) throws DaoException;

	public Role find(int id) throws DaoException;
	
	public Role find(String name) throws DaoException;
	
	public boolean insert(Role role) throws DaoException;
	
	public boolean update(Role role) throws DaoException;
	
	public boolean delete(Role role) throws DaoException;

}
