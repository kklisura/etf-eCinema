package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.dao.mapper.PrivilegeRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Privilege;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class PrivilegeDaoImpl implements PrivilegeDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new PrivilegeRowMapper();
	
	
	public PrivilegeDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Privilege> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Privileges");
	}

	@Override
	public Privilege find(int id) throws DaoException 
	{		
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Privileges WHERE id = ?", id);	
	}

	@Override
	public boolean insert(Privilege privilege) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
										  "INSERT INTO Privileges (allow, resources_id, roles_id, privilegetypes_id) VALUES (?, ?, ?, ?)",
										  privilege.isAllowed(),
										  privilege.getResource().getId(),
										  privilege.getRole().getId(),
										  privilege.getPrivilegeType().getId());
		
		privilege.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Privilege privilege) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,
							  "UPDATE Privileges SET allow = ?, resources_id = ?, roles_id = ?, privilegetypes_id = ? WHERE id = ?",
							  privilege.isAllowed(),
							  privilege.getResource().getId(),
							  privilege.getRole().getId(),
							  privilege.getPrivilegeType().getId(),
							  privilege.getId());
		
		return true;
	}

	@Override
	public boolean delete(Privilege privilege) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Privileges WHERE id = ?", privilege.getId());
		
		return true;
	}

}
