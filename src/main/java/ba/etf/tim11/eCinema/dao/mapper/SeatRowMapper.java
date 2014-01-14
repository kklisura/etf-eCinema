package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.models.Reservation;
import ba.etf.tim11.eCinema.models.Seat;


public class SeatRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		Seat seat = new Seat();

		seat.setId(rs.getInt(1));
		seat.setRow(rs.getInt(2));
		seat.setCol(rs.getInt(3));
		
		CinemaHall cinemaHall = new CinemaHall();
		cinemaHall.setId(rs.getInt(4));
		seat.setCinemaHall(cinemaHall);
		
		Reservation reservation = new Reservation();	
		reservation.setId(rs.getInt(5));
		seat.setReservation(reservation);
		
		seat.setUpdatedAt(rs.getDate(6));
		seat.setCreatedAt(rs.getDate(7));
		
		return seat;
	}
	
}
