package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.UserAction;



public interface UserActionDao 
{
	public List<UserAction> findAll();
	
	public UserAction find(int id);
	
	public boolean insert(UserAction userAction);
	
	public boolean update(UserAction userAction);
	
	

}
