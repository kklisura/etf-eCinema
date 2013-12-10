package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.CinemaDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserRowMapper;
import ba.etf.tim11.eCinema.models.Cinema;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class CinemaDaoImpl implements CinemaDao
{
	
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserRowMapper();
	
	
	public CinemaDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Cinema> findAll(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Cinemas LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Cinema find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Cinemas WHERE id = ?", 
											 id);
	}

	@Override
	public boolean insert(Cinema cinema) throws DaoException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cinema cinema) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cinema cinema) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Cinemas WHERE id = ?", cinema.getId());
		
		return true;
	}

}
