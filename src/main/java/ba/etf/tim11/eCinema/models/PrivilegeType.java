package ba.etf.tim11.eCinema.models;

public class PrivilegeType
{
	int id;
	String name;
	
	
	public PrivilegeType(int id, String name) 
	{	
		this.id = id;
		this.name = name;
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
	
}
