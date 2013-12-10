package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.Reservation;
import ba.etf.tim11.eCinema.models.ReservationType;
import ba.etf.tim11.eCinema.models.User;

public class ReservationRowMapper implements RowMapper 
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Reservation reservation = new Reservation();
		Projection projection = new Projection();
		User user = new User();
		ReservationType reservationType = new ReservationType();
		
		reservation.setId(rs.getInt(1));
		
		projection.setId(rs.getInt(2));
		reservation.setProjection(projection);
		
		user.setId(rs.getInt(3));
		reservation.setUser(user);
		
		reservationType.setId(rs.getInt(4));
		reservation.setReservationType(reservationType);
		
		reservation.setUpdatedAt(rs.getDate(5));
		reservation.setCreatedAt(rs.getDate(6));
		
		return reservation;
	}

}
