package ba.etf.tim11.eCinema.service;

import java.util.List;

import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.models.UserAction;


public interface UserService 
{
	public List<User> findAll(int offset, int limit);
	
	public int numberOfUsers();
	
	public void insert(User user);
	
	public User find(int id);
	
	public void update(User user);
	
	public void delete(User user);
	
	public User getUser(String usernameOrEmail);
	
	public List<Group> getUserGroups(User user);
	
	public List<Role> getUserRoles(User user);
	
	public List<UserAction> getUserActions(User user, int offset, int limit);
	
}
