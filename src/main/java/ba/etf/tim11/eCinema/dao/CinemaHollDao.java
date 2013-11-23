package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.CinemaHoll;

public interface CinemaHollDao
{
	public List<CinemaHoll> findAll() throws DaoException;

	public CinemaHoll find(int id) throws DaoException;
	
	public CinemaHoll find(String type) throws DaoException;
	
	public CinemaHoll insert(CinemaHoll cinemaHoll) throws DaoException;
	
	public boolean update(CinemaHoll cinemaHoll) throws DaoException;
	
	public boolean delete(CinemaHoll cinemaHoll) throws DaoException;
}
