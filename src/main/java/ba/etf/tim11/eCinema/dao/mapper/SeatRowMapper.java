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
<<<<<<< HEAD
		CinemaHall cinemaHall= new CinemaHall();
=======
		CinemaHall cinemaHoll= new CinemaHall();
>>>>>>> 5c53434c019cbc885939f1fd20a53e4b0b0da982
		Reservation reservation = new Reservation();
		
		seat.setId(rs.getInt(1));
		seat.setId(rs.getInt(2));
		
		cinemaHall.setId(rs.getInt(3));
		seat.setCinemaHall(cinemaHall);
		
		reservation.setId(rs.getInt(4));
		seat.setReservation(reservation);
		
		seat.setUpdatedAt(rs.getDate(5));
		seat.setCretedAt(rs.getDate(6));
		
		return seat;
	}

}
