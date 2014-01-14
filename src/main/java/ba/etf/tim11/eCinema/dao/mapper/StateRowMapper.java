package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.State;


public class StateRowMapper implements RowMapper 
{
	
	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		State state = new State();
		
		state.setId(rs.getInt(1));
		state.setName(rs.getString(2));
		state.setShortName(rs.getString(3));
		state.setUpdatedAt(rs.getDate(4));
		state.setCreatedAt(rs.getDate(5));
		
		return state;
	}
	
}
