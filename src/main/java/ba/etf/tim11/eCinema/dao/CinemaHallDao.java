package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.CinemaHall;

public interface CinemaHallDao
{
	public List<CinemaHall> findAll() throws DaoException;
	
	public List<CinemaHall> findAllByCinema(int cinemaId) throws DaoException;

	public CinemaHall find(int id) throws DaoException;
	
	public boolean insert(CinemaHall cinemaHall) throws DaoException;
	
	public boolean update(CinemaHall cinemaHall) throws DaoException;
	
	public boolean delete(CinemaHall cinemaHall) throws DaoException;
}
