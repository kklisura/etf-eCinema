package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Resource;


public interface ResourceDao 
{
	public List<Resource> findAll();
	
	public Resource find(int id);
	
	public boolean insert(Resource user);
	
	public boolean update(Resource user);
	
	public Resource findByName(String name);

}
