package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.PrivilegeType;


public interface PrivilegeTypeDao 
{
	public List<PrivilegeType> findAll();

	public PrivilegeType find(int id);

	public boolean insert(PrivilegeType privilegeType);

	public boolean update(PrivilegeType privilegeType);
	
	public PrivilegeType findByName(String name);

}
