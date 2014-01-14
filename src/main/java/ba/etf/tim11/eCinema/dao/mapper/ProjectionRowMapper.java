package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.CinemaHall;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.ProjectionType;


public class ProjectionRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Projection projection = new Projection();
		
		projection.setId(rs.getInt(1));
		projection.setTime(rs.getTimestamp(2));
		projection.setPricePerSeat(rs.getBigDecimal(3));
		
		Content content = new Content();
		content.setId(rs.getInt(4));
		projection.setContent(content);
		
		CinemaHall cinemaHall = new CinemaHall();
		cinemaHall.setId(rs.getInt(5));
		projection.setCinemaHall(cinemaHall);
		
		ProjectionType projectionType = new ProjectionType();
		projectionType.setId(rs.getInt(6));
		projection.setProjectionType(projectionType);
		
		projection.setUpdatedAt(rs.getDate(7));
		projection.setCreatedAt(rs.getDate(8));
		
		return projection;
	}
	
}
