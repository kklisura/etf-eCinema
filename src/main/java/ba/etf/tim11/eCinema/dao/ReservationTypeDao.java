package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.ReservationType;


public interface ReservationTypeDao
{
	public List<ReservationType> findAll(int offset, int limit) throws DaoException;

	public ReservationType find(int id) throws DaoException;
	
	public ReservationType find(String type) throws DaoException;
	
	public boolean insert(ReservationType reservationType) throws DaoException;
	
	public boolean update(ReservationType reservationType) throws DaoException;
	
	public boolean delete(ReservationType reservationType) throws DaoException;

}
