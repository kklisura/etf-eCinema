package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Language;
import ba.etf.tim11.eCinema.models.State;

public class LanguageRowMapper implements RowMapper
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Language language = new Language();
		
		language.setId(rs.getInt(1));
		language.setLanguage(rs.getString(2));
		
		State state = new State();
		state.setId(rs.getInt(3));
		language.setState(state);
		
		language.setUpdatedAt(rs.getDate(4));
		language.setCreatedAt(rs.getDate(5));
		
		return language;
	}

}
