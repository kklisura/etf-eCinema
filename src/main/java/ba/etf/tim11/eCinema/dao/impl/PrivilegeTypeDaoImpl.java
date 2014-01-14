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
	public List<PrivilegeType> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM PrivilegeTypes LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public PrivilegeType find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM PrivilegeTypes WHERE id = ?", id);
	}

	@Override
	public PrivilegeType find(String name) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM PrivilegeTypes WHERE name = ?", name);
	}

	@Override
	public boolean insert(PrivilegeType privilegeType) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, "INSERT INTO PrivilegeTypes (name) VALUES (?)", privilegeType.getName());
		
		privilegeType.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(PrivilegeType privilegeType) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE PrivilegeTypes SET name = ? WHERE id = ?", 
							  privilegeType.getName(),
							  privilegeType.getId());
		
		return true;
	}

	@Override
	public boolean delete(PrivilegeType privilegeType) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM PrivilegeTypes WHERE id = ?", privilegeType.getId());
		
		return true;
	}
	
}
