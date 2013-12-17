package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Type;

public class TypeRowMapper implements RowMapper
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Type type = new Type();
		type.setId(rs.getInt(1));
		type.setType(rs.getString(2));
		type.setUpdatedAt(rs.getDate(3));
		type.setCreatedAt(rs.getDate(4));
		
		return type;
	}

}
