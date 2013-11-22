package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Projection;

public interface ProjectionDao 
{
	public List<Projection> findAll() throws DaoException;
	
	public Projection find (int id) throws DaoException;
	
	public Projection insert (Projection projection) throws DaoException;
	
	public boolean update (Projection projection) throws DaoException;
	
	public boolean delete (Projection projection) throws DaoException;

}
