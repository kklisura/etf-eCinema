package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.SeatDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserRowMapper;
import ba.etf.tim11.eCinema.models.Seat;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class SeatDaoImpl implements SeatDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserRowMapper();
	
	
	public SeatDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	
	@Override
	public List<Seat> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Seats LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Seat find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Seats WHERE id = ?", 
											 id);
	}

	@Override
	public Seat findSeatNumber(int seatNumber) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Seats WHERE seatNumber = ?", 
											 seatNumber);
	}

	@Override
	public boolean insert(Seat seat) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
											"INSERT INTO Seats (seatNumber, cinemahalls_id, reservations_id) VALUES (?, ?, ?)",
											seat.getSeatNumber(),
											seat.getCinemaHall().getId(),
											seat.getReservation().getId());
		seat.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Seat seat) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
								"UPDATE Seats SET seatNumber = ?, cinemahalls_id = ?, reservations_id = ? WHERE = ?",
								seat.getSeatNumber(),
								seat.getCinemaHall().getId(),
								seat.getReservation().getId(),
								seat.getId());
		return true;
	}

	@Override
	public boolean delete(Seat seat) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Seats WHERE id = ?", seat.getId());
		
		return true;
	}

}
