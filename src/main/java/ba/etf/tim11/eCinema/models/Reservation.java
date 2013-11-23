package ba.etf.tim11.eCinema.models;

import java.util.Date;

public class Reservation
{
	int id;
	Projection projection;
	User user;
	Receipt receipt;
	ReservationType reservationType;
	Date createdAt;
	Date updatedAt;
	
	public Reservation() {}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Projection getProjection() {
		return projection;
	}
	
	public void setProjection(Projection projection) {
		this.projection = projection;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Receipt getReceipt() {
		return receipt;
	}
	
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	public ReservationType getReservationType() {
		return reservationType;
	}
	
	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
