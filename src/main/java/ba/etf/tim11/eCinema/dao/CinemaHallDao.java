package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.CinemaHall;


public interface CinemaHallDao
{
	public List<CinemaHall> findAll(int offset, int limit) throws DaoException;
	
	public List<CinemaHall> findAllByCinema(int cinemaId, int offset, int limit) throws DaoException;

	public CinemaHall find(int id) throws DaoException;
	
	public boolean insert(CinemaHall cinemaHall) throws DaoException;
	
	public boolean update(CinemaHall cinemaHall) throws DaoException;
	
	public boolean delete(CinemaHall cinemaHall) throws DaoException;

}
