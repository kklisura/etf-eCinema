package ba.etf.tim11.eCinema.models;

import java.util.Date;

public class State 
{
	int id;
	String name;
	String shortName;
	Date updatedAt;
	Date createdAt;
	
	
	public State(int id, String name, String shortName, Date updatedAt,
				Date createdAt) {
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
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
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
