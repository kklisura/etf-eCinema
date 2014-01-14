package ba.etf.tim11.eCinema.models;

import java.util.Date;


public class CinemaHall 
{
	private int id;
	
	private String title;
	private int rows, cols;
	private String seatMatrix;
	private Cinema cinema;
	
	private Date updatedAt, createdAt;

	
	public CinemaHall() {
		
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
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public void setCols(int cols) {
		this.cols = cols;
	}
	
	public String getSeatMatrix() {
		return seatMatrix;
	}
	
	public void setSeatMatrix(String seatMatrix) {
		this.seatMatrix = seatMatrix;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
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
	
	public void setCretedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
