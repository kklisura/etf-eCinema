package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.User;


public interface UserDao
{
	
	//NOTE(kklisura): Use custom exception (!?)
	
	public List<User> findAll();
	
	public User find(int id);
	
	public boolean insert(User user);
	
	public boolean update(User user);
	
	public User findByEmail(String email);
}

