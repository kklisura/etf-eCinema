package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.AudioSynchronization;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Language;

public class AudioSynchronizationRowMapper implements RowMapper
{

	@Override
	public Object map(ResultSet rs) throws SQLException
	{
		AudioSynchronization audioSynchronization = new AudioSynchronization();
				
		audioSynchronization.setId(rs.getInt(1));
		
		Content content = new Content();
		content.setId(rs.getInt(2));
		audioSynchronization.setContent(content);
		
		Language language = new Language ();
		language.setId(rs.getInt(3));
		audioSynchronization.setLanguage(language);
		
		audioSynchronization.setFileId(rs.getString(4));
		audioSynchronization.setUpdatedAt(rs.getDate(5));
		audioSynchronization.setCreatedAt(rs.getDate(6));
		
		return audioSynchronization;
	}

}
