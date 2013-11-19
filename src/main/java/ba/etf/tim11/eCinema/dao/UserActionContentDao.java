package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.UserActionContent;


public interface UserActionContentDao
{
	
	public List<UserActionContent> findAll();
	
	public UserActionContent find(int id);
	
	public boolean insert(UserActionContent userActionContent);
	
	public boolean update(UserActionContent userActionContent);
	
}