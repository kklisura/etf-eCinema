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
		CinemaHall cinemaHoll= new CinemaHall();
		Reservation reservation = new Reservation();
		
		seat.setId(rs.getInt(1));
		seat.setId(rs.getInt(2));
		
		cinemaHoll.setId(rs.getInt(3));
		seat.setCinemaHoll(cinemaHoll);
		
		reservation.setId(rs.getInt(4));
		seat.setReservation(reservation);
		
		seat.setUpdatedAt(rs.getDate(5));
		seat.setCretedAt(rs.getDate(6));
		
		return seat;
	}

}
