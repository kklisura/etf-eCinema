package ba.etf.tim11.eCinema.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Reservation
{
	private int id;
	
	private Projection projection;
	private User user;
	private Receipt receipt;
	private ReservationType reservationType;
	private List<Seat> seats;
	
	private Date createdAt, updatedAt;
	
	
	public Reservation() {
		seats = new ArrayList<Seat>();
	}

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
	
	public List<Seat> getSeats() {
		return seats;
	}
	
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
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
	
	
	public void addSeat(Seat seat) {
		seats.add(seat);
	}
	
}
