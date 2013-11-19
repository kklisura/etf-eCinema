package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Role;


public interface RoleDao
{
public List<Role> findAll();
	
	public Role find(int id);
	
	public boolean insert(Role role);
	
	public boolean update(Role role);
	
	public Role findByName(String name);

}
