package ba.etf.tim11.eCinema.models;

import java.util.Date;

public class Seat
{
	int id;
	int seatNumber;
	CinemaHoll cinemaHoll;
	Reservation reservation;
	Date updatedAt;
	Date cretedAt;
	
	public Seat(int id, int seatNumber, CinemaHoll cinemaHoll,
			Reservation reservation, Date updatedAt, Date cretedAt) 
	{
		this.id = id;
		this.seatNumber = seatNumber;
		this.cinemaHoll = cinemaHoll;
		this.reservation = reservation;
		this.updatedAt = updatedAt;
		this.cretedAt = cretedAt;
	}
	
	public Seat() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public CinemaHoll getCinemaHoll() {
		return cinemaHoll;
	}
	
	public void setCinemaHoll(CinemaHoll cinemaHoll) {
		this.cinemaHoll = cinemaHoll;
	}
	
	public Reservation getReservation() {
		return reservation;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
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
