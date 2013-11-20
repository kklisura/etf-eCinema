package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.User;

public class UserRowMapper implements RowMapper {

	@Override
	public Object map(ResultSet rs) throws SQLException {
		
		User user = new User();
		
		user.setId(rs.getInt(1));
		return null;
	}

}
