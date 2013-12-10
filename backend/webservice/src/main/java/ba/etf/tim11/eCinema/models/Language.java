package ba.etf.tim11.eCinema.models;

import java.util.Date;


public class Language 
{
	int id;
	String language;
	State state;
	Date updatedAt, createdAt;
		
	public Language() {}
	
	

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



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	

}
