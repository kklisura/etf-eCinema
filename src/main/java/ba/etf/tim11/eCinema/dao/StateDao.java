package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.State;


public interface StateDao 
{
	public List<State> findAll() throws DaoException;
	
	public State find(int id) throws DaoException;
	
	public State findByName(String name) throws DaoException;
	
	public State findByShortName(String shortName) throws DaoException;
	
	public State insert(State state) throws DaoException;
	
	public boolean update(State state) throws DaoException;
	
	public boolean delete(State state) throws DaoException;
}
