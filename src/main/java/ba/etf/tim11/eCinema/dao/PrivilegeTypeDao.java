package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.PrivilegeType;


public interface PrivilegeTypeDao 
{
	public List<PrivilegeType> findAll(int offset, int limit) throws DaoException;

	public PrivilegeType find(int id) throws DaoException;
	
	public PrivilegeType find(String name) throws DaoException;

	public boolean insert(PrivilegeType privilegeType) throws DaoException;

	public boolean update(PrivilegeType privilegeType) throws DaoException;
	
	public boolean delete(PrivilegeType privilegeType) throws DaoException;
}
