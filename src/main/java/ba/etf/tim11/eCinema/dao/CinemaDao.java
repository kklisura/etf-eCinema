package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Cinema;


public interface CinemaDao
{
	public List<Cinema> findAll(int offset, int limit) throws DaoException;
	
	public Cinema find(int id) throws DaoException;
	
	public boolean insert(Cinema cinema) throws DaoException;
	
	public boolean update(Cinema cinema) throws DaoException;
	
	public boolean delete(Cinema cinema) throws DaoException;
	
}
