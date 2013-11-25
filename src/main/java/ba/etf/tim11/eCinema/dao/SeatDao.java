package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Seat;

public interface SeatDao
{
	public List<Seat> findAll() throws DaoException;
	
	public Seat find(int id) throws DaoException;
	
	public Seat findSeatNumber(int seatNumber) throws DaoException;
	
	public boolean insert(Seat seat) throws DaoException;
	
	public boolean update(Seat seat) throws DaoException;
	
	public boolean delete(Seat seat) throws DaoException;

}
