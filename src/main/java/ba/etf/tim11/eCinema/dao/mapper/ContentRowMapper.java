package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;

import ba.etf.tim11.eCinema.models.Content;


public class ContentRowMapper implements RowMapper 
{
	@Override
	public Object map(ResultSet rs) 
	{
		Content content = new Content();
		// TODO Auto-generated method stub
		return content;
	}

}
