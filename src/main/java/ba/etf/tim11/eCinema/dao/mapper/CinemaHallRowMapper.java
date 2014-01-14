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
		
		
		cinemaHall.setId(rs.getInt(1));
		cinemaHall.setTitle(rs.getString(2));
		
		cinemaHall.setRows(rs.getInt(3));
		cinemaHall.setCols(rs.getInt(4));
		cinemaHall.setSeatMatrix(rs.getString(5));
		
		Cinema cinema = new Cinema();
		cinema.setId(rs.getInt(6));
		cinemaHall.setCinema(cinema);
		
		cinemaHall.setUpdatedAt(rs.getDate(7));
		cinemaHall.setCretedAt(rs.getDate(8));
		
		return cinemaHall;
	}

}
