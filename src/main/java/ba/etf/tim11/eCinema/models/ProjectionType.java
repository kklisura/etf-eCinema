package ba.etf.tim11.eCinema.models;

import java.util.Date;

public class ProjectionType
{
	int id;
	String type;
	Date updatedAt;
	Date cretedAt;
	
	public ProjectionType(int id, String type, Date updatedAt, Date cretedAt)
	{
		this.id = id;
		this.type = type;
		this.updatedAt = updatedAt;
		this.cretedAt = cretedAt;
	}
	
	public ProjectionType() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCretedAt() {
		return cretedAt;
	}
	
	public void setCretedAt(Date cretedAt) {
		this.cretedAt = cretedAt;
	}
}
