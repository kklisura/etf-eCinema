package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.CinemaHallDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserRowMapper;
import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class CinemaHallDaoImpl implements CinemaHallDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserRowMapper();
	
	
	public CinemaHallDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	
	@Override
	public List<CinemaHall> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM CinemaHalls LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public List<CinemaHall> findAllByCinema(int cinemaId, int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM CinemaHalls WHERE cinemas_id = ? LIMIT ?, ?", 
									cinemaId,
									offset,
									limit);
	}
	
	@Override
	public CinemaHall find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM CinemaHalls WHERE id = ?", 
											 id);
	}

	@Override
	public boolean insert(CinemaHall cinemaHall) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
											"INSERT INTO CinemaHalls (title, cinemas_id, numberOfSeats) VALUES (?, ?, ?)",
											cinemaHall.getTitle(),
											cinemaHall.getCinema().getId(),
											cinemaHall.getNumberOfSeat());
		cinemaHall.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(CinemaHall cinemaHall) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,
								"UPDATE CinemaHalls SET title = ?, cinemas_id = ?, numberOfSeats = ? WHERE id = ?",
								cinemaHall.getTitle(),
								cinemaHall.getCinema().getId(),
								cinemaHall.getNumberOfSeat(),
								cinemaHall.getId());
		return true;
	}

	@Override
	public boolean delete(CinemaHall cinemaHall) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM CinemaHalls WHERE id = ?", cinemaHall.getId());
		
		return true;
	}

}
