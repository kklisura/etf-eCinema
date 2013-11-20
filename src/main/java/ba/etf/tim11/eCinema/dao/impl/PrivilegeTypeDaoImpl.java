package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.PrivilegeTypeDao;
import ba.etf.tim11.eCinema.dao.mapper.PrivilegeTypeRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.PrivilegeType;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class PrivilegeTypeDaoImpl implements PrivilegeTypeDao 
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new PrivilegeTypeRowMapper();
	
	
	public PrivilegeTypeDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<PrivilegeType> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM PrivilegeTypes", rowMapper);
	}

	@Override
	public PrivilegeType find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM PrivilegeTypes WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public PrivilegeType findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(PrivilegeType privilegeType) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PrivilegeType privilegeType) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
