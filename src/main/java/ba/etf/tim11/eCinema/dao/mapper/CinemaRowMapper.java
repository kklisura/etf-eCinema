package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Cinema;

public class CinemaRowMapper implements RowMapper 
{

	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		Cinema cinema = new Cinema();
		
		cinema.setId(rs.getInt(1));
		cinema.setUpdatedAt(rs.getDate(2));
		cinema.setCretedAt(rs.getDate(3));
		
		return cinema;
	}
}
