package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.StateRowMapper;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class StateDaoImpl implements StateDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new StateRowMapper();
	
	
	public StateDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<State> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM States", rowMapper);
	}

	@Override
	public State find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM States WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public boolean insert(State state) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(State state) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public State findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State findByShortName(String shortName) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
