package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Cinema;
import ba.etf.tim11.eCinema.models.CinemaHoll;

public class CinemaHollRowMapper implements RowMapper 
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		CinemaHoll cinemaHoll = new CinemaHoll();
		Cinema cinema = new Cinema();
		
		cinemaHoll .setId(rs.getInt(1));
		cinemaHoll .setTitle(rs.getString(2));
		
		cinema.setId(rs.getInt(3));
		cinemaHoll .setCinema(cinema);
		
		cinemaHoll .setNumberOfSeat(rs.getInt(4));
		cinemaHoll .setUpdatedAt(rs.getDate(5));
		cinemaHoll .setCretedAt(rs.getDate(6));
		
		return cinemaHoll;
	}

}
