package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.ReservationType;


public class ReservationTypeRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		ReservationType reservationType = new ReservationType();
		
		reservationType.setId(rs.getInt(1));
		reservationType.setType(rs.getString(2));
		reservationType.setUpdatedAt(rs.getDate(3));
		reservationType.setCreatedAt(rs.getDate(4));
		
		return reservationType;
	}
	
}
