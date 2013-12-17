package ba.etf.tim11.eCinema.service;

import ba.etf.tim11.eCinema.models.User;


public interface LoginService 
{
	public boolean validateSession(String username, String key);
	
	public String encryptPassword(String password);
	
	public User getCurrentUser();
}
