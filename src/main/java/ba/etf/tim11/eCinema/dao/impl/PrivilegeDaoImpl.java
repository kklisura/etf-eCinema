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
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Privileges", rowMapper);
	}

	@Override
	public Privilege find(int id) throws DaoException 
	{		
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Privileges WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);	
	}

	@Override
	public boolean insert(Privilege user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Privilege user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
