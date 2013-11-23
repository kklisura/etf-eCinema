package ba.etf.tim11.eCinema.models;

import java.util.Date;

public class CinemaHoll 
{
	int id;
	String title;
	Cinema cinema;
	int numberOfSeat;
	Date updatedAt;
	Date cretedAt;

	public CinemaHoll(int id, String title, Cinema cinema, int numberOfSeat,
			Date updatedAt, Date cretedAt) 
	{
		this.id = id;
		this.title = title;
		this.cinema = cinema;
		this.numberOfSeat = numberOfSeat;
		this.updatedAt = updatedAt;
		this.cretedAt = cretedAt;
	}
	
	public CinemaHoll() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public int getNumberOfSeat() {
		return numberOfSeat;
	}
	
	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
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
