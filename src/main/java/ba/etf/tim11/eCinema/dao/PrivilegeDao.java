package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Privilege;


public interface PrivilegeDao
{
    public List<Privilege> findAll(int offset, int limit) throws DaoException;
	
    public List<Privilege> findAllByRole(int roleId, int offset, int limit) throws DaoException;
    
	public Privilege find(int id) throws DaoException;
	
	public boolean insert(Privilege privilege) throws DaoException;
	
	public boolean update(Privilege privilege) throws DaoException;
	
	public boolean delete(Privilege privilege) throws DaoException;
	
	public boolean isAllowed(String username, int resourceId, int privilegeTypeId) throws DaoException;

}
