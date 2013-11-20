package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Content;


public interface ContentDao 
{
	    public List<Content> findAll();
		
		public Content find(int id);
		
		public boolean insert(Content content);
		
		public boolean update(Content content);
		
		
}
