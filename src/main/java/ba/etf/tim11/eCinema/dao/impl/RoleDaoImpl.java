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
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Roles");
	}

	@Override
	public Role find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Roles WHERE id = ?", id);
	}
	
	@Override
	public Role find(String name) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Roles WHERE name = ?", name);
	}

	@Override
	public boolean insert(Role role) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
										  "INSERT INTO Roles (name, description) VALUES (?, ?)",
										  role.getName(),
										  role.getDescription());
		
		role.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Role role) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,
							  "UPDATE Roles SET name = ?, description = ? WHERE id = ?",
							  role.getName(),
							  role.getDescription(),
							  role.getId());
		
		return true;
	}

	@Override
	public boolean delete(Role role) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Roles WHERE id = ?", role.getId());
		
		return true;
	}
	
}
