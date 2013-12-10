package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Language;
import ba.etf.tim11.eCinema.models.Subtitle;

public class SubtitleRowMapper implements RowMapper
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		Subtitle subtitle = new Subtitle();
		
		subtitle.setId(rs.getInt(1));
		
		Language language = new Language();
		language.setId(rs.getInt(2));
		subtitle.setLanguage(language);
		
		Content content = new Content();
		content.setId(rs.getInt(3));
		subtitle.setContent(content);
		
		subtitle.setFileId(rs.getString(4));
		subtitle.setUpdatedAt(rs.getDate(5));
		subtitle.setCreatedAt(rs.getDate(6));
		
		return subtitle;
	}

}
