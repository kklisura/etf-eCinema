package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Cinema;
import ba.etf.tim11.eCinema.models.CinemaHall;

public class CinemaHallRowMapper implements RowMapper 
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		CinemaHall cinemaHall = new CinemaHall();
		Cinema cinema = new Cinema();
		
		cinemaHall .setId(rs.getInt(1));
		cinemaHall .setTitle(rs.getString(2));
		
		cinema.setId(rs.getInt(3));
		cinemaHall .setCinema(cinema);
		
		cinemaHall .setNumberOfSeat(rs.getInt(4));
		cinemaHall .setUpdatedAt(rs.getDate(5));
		cinemaHall .setCretedAt(rs.getDate(6));
		
		return cinemaHall;
	}

}
