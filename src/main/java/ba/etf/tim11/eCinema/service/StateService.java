package ba.etf.tim11.eCinema.service;

import java.util.List;

import ba.etf.tim11.eCinema.models.State;


public interface StateService 
{
	public List<State> findAll(int offset, int limit);
	
	public State find(int id);
	
	public boolean insert(State state);
	
	public boolean update(State state);
	
	public boolean delete(State state);
	
}
