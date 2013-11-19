package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.UserActionType;


public interface UserActionTypeDao
{
	
	public List<UserActionType> findAll();
	
	public UserActionType find(int id);
	
	public boolean insert(UserActionType userActionType);
	
	public boolean update(UserActionType userActionType);
	
}
