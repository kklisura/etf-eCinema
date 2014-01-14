package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ReservationTypeDao;
import ba.etf.tim11.eCinema.dao.mapper.ReservationTypeRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.ReservationType;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class ReservationTypeDaoImpl implements ReservationTypeDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new ReservationTypeRowMapper();
	
	
	public ReservationTypeDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<ReservationType> findAll(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM ReservationTypes LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public ReservationType find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM ReservationTypes WHERE id = ?", 
											 id);
	}

	@Override
	public ReservationType find(String type) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM ReservationTypes WHERE type = ?", 
											 type);
	}

	@Override
	public boolean insert(ReservationType reservationType) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
											"INSERT INTO ReservationTypes(type) VALUES (?)",
											reservationType.getType());
		reservationType.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(ReservationType reservationType) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
								"UPDATE ReservationTypes SET type = ? WHERE id = ?",
								reservationType.getType(),
								reservationType.getId());
		return true;
	}

	@Override
	public boolean delete(ReservationType reservationType) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM ReservationTypes WHERE id = ?", reservationType.getId());
		
		return true;
	}
	
}
