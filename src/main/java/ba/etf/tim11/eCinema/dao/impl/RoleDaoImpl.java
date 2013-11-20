package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.mapper.RoleRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class RoleDaoImpl implements RoleDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new RoleRowMapper();
	
	
	public RoleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<Role> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Roles", rowMapper);
	}

	@Override
	public Role find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Roles WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public boolean insert(Role role) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Role role) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Role findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
