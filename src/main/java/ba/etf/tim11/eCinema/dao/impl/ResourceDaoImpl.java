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
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Resources", rowMapper);
	}

	@Override
	public Resource find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Resources WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public boolean insert(Resource user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Resource user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Resource findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
