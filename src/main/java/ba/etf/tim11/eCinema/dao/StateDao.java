package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.State;


public interface StateDao 
{
	public List<State> findAll();
	
	public State find(int id);
	
	public boolean insert(State state);
	
	public boolean update(State state);
	
	public State findByName(String name);
	
	public State findByShortName(String shortName);

}
