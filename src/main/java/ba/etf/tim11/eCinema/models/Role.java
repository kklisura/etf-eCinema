package ba.etf.tim11.eCinema.models;

import java.util.ArrayList;
import java.util.List;


public class Role 
{
	private int id;
	
	private String name, description;
	private List<Privilege> privileges;
	
	
	public Role() {
		privileges = new ArrayList<Privilege>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	
	public void addPrivilege(Privilege privilege) {
		privileges.add(privilege);
	}
	
}
