package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.CinemaHoll;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.ProjectionType;

public class ProjectionRowMapper implements RowMapper 
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Projection projection = new Projection();
		Content content = new Content();
		CinemaHoll cinemaHoll = new CinemaHoll();
		ProjectionType projectionType = new ProjectionType();
		
		projection.setId(rs.getInt(1));
		projection.setTime(rs.getDate(2));
		projection.setPricePerSeat(rs.getBigDecimal(3));
		
		content.setId(rs.getInt(4));
		projection.setContent(content);
		cinemaHoll.setId(rs.getInt(5));
		projection.setCinemaHoll(cinemaHoll);
		projectionType.setId(rs.getInt(6));
		projection.setProjectionType(projectionType);
		
		projection.setUpdatedAt(rs.getDate(7));
		projection.setCreatedAt(rs.getDate(8));
		
		return projection;
	}

}
