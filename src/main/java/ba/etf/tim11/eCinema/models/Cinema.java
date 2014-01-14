package ba.etf.tim11.eCinema.models;

import java.util.Date;


public class Cinema
{
	private int id;
	
	private String name, address;
	
	private Date updatedAt, createdAt;

	
	public Cinema()  {
		
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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
	
	public void setCreatedAt(Date craetedAt) {
		this.createdAt = craetedAt;
	}
	
}
