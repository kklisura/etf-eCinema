package ba.etf.tim11.eCinema.models;

public class Privilege 
{
	int id;
	boolean allow;
	Resource resource;
	Role role;
	PrivilegeType privilegeType;
	
	
	public Privilege(int id, boolean allow, Resource resource, Role role,
					 PrivilegeType privilegeType) 
	{	
		this.id = id;
		this.allow = allow;
		this.resource = resource;
		this.role = role;
		this.privilegeType = privilegeType;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Boolean getAllow() {
		return allow;
	}
	
	public void setAllow(Boolean allow) {
		this.allow = allow;
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public PrivilegeType getPrivilegeType() {
		return privilegeType;
	}
	
	public void setPrivilegeType(PrivilegeType privilegeType) {
		this.privilegeType = privilegeType;
	}
	
}
