package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Cinema;

public interface CinemaDao
{
	public List<Cinema> findAll() throws DaoException;
	
	public Cinema find(int id) throws DaoException;

}
