package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.mapper.ResourceRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Resource;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class ResourceDaoImpl implements ResourceDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new ResourceRowMapper();
	
	
	public ResourceDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Resource> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Resources");
	}

	@Override
	public Resource find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Resources WHERE id = ?", id);
	}
	
	@Override
	public Resource find(String name) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Resources WHERE name = ?", name);
	}
	
	@Override
	public boolean insert(Resource resource) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,	
										  "INSERT INTO Resources (name, description) VALUES (?, ?)",
										  resource.getName(),
										  resource.getDescription());
		
		resource.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Resource resource) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,	
							  "UPDATE Resources SET name = ?, description = ? WHERE id = ?",
							  resource.getName(),
							  resource.getDescription(),
							  resource.getId());
		
		return true;
	}


	@Override
	public boolean delete(Resource resource) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Resources WHERE id = ?", resource.getId());
		
		return true;
	}

}
