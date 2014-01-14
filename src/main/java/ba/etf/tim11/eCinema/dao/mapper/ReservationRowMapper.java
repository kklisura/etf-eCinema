package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.Receipt;
import ba.etf.tim11.eCinema.models.Reservation;
import ba.etf.tim11.eCinema.models.ReservationType;
import ba.etf.tim11.eCinema.models.User;


public class ReservationRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Reservation reservation = new Reservation();
		
		reservation.setId(rs.getInt(1));
		
		Projection projection = new Projection();
		projection.setId(rs.getInt(2));
		reservation.setProjection(projection);
		
		User user = new User();
		user.setId(rs.getInt(3));
		reservation.setUser(user);
		
		Receipt receipt = new Receipt();
		receipt.setId(rs.getInt(4));
		reservation.setReceipt(receipt);
		
		ReservationType reservationType = new ReservationType();
		reservationType.setId(rs.getInt(5));
		reservation.setReservationType(reservationType);
		
		reservation.setUpdatedAt(rs.getDate(6));
		reservation.setCreatedAt(rs.getDate(7));
		
		return reservation;
	}
	
}
