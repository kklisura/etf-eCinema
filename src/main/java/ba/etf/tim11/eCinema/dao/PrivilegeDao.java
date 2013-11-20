package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Privilege;


public interface PrivilegeDao
{
    public List<Privilege> findAll();
	
	public Privilege find(int id);
	
	public boolean insert(Privilege user);
	
	public boolean update(Privilege user);	

}
