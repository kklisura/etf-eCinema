package ba.etf.tim11.eCinema.models;

import java.util.Date;

public class Cinema
{
	int id;
	Date updatedAt;
	Date cretedAt;

	public Cinema(int id, Date updatedAt, Date cretedAt) 
	{
		this.id = id;
		this.updatedAt = updatedAt;
		this.cretedAt = cretedAt;
	}
	
	public Cinema() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
