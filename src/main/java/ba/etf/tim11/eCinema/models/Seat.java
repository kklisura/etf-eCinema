package ba.etf.tim11.eCinema.models;

import java.util.Date;

public class Seat
{
	int id;
	int seatNumber;
	CinemaHall cinemaHall;
	Reservation reservation;
	Date updatedAt;
	Date cretedAt;
	
	public Seat() {}

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
	
	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}
	
	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
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
