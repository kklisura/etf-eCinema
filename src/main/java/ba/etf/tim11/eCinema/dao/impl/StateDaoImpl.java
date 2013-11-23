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
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM States");
	}

	@Override
	public State find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM States WHERE id = ?", id);
	}
	
	@Override
	public State find(String name) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM States WHERE name = ?", name);
	}

	@Override
	public State findByShortName(String shortName) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM States WHERE shortName = ?", shortName);
	}

	@Override
	public boolean insert(State state) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										 "INSERT INTO States (name, shortName) VALUES (?, ?)",
										 state.getName(),
										 state.getShortName());
		
		state.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(State state) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE States SET name = ?, shortName = ? WHERE id = ?",
							  state.getName(),
							  state.getShortName(),
							  state.getId());
		
		return true;
	}
	
	@Override
	public boolean delete(State state) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM States WHERE id = ?", state.getId());
		
		return true;
	}
	
}
