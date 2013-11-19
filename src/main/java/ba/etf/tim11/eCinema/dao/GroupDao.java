package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Group;


public interface GroupDao 
{
	public List<Group> findAll();
	
	public Group find(int id);
	
	public boolean insert(Group group);
	
	public boolean update(Group group);
	
	public Group findByName(String name);

}
