package ba.etf.tim11.eCinema.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties({"role"})
public class Privilege 
{
	private int id;
	
	private boolean allowed;
	
	private Resource resource;
	private Role role;
	private PrivilegeType privilegeType;
	
	
	public Privilege() {
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isAllowed() {
		return allowed;
	}
	
	public void setIsAllowed(boolean allowed) {
		this.allowed = allowed;
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
