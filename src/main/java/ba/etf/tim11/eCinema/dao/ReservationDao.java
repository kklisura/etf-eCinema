package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Reservation;


public interface ReservationDao 
{
	public List<Reservation> findAll(int offset, int limit) throws DaoException;
	
	public List<Reservation> findAllByProjection(int projectionId, int offset, int limit) throws DaoException;
	
	public Reservation find(int id) throws DaoException;
	
	public boolean insert(Reservation reservation) throws DaoException;
	
	public boolean update(Reservation reservation) throws DaoException;
	
	public boolean delete(Reservation reservation) throws DaoException;

}
