package ba.etf.tim11.eCinema.service;

import java.util.List;

import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.Tag;
import ba.etf.tim11.eCinema.models.TopContent;


public interface ContentService 
{
    public List<Content> findAll(int offset, int limit);

    public List<Content> findRecentlyAdded(int offset, int limit);

    public List<Content> findBestRated(int offset, int limit);

    public List<TopContent> findTop(int offset, int limit);
    
    public TopContent findTop(int id);

    public List<Content> findAllByFilter(String filter, int offset, int limit);
    
	public List<Tag> findAllTags(int offset, int limit);
	
	public Tag findTag(int tagId);
	 
	public Content find(int id);
	
	public List<Content> findInTheaters(int offset, int limit);
	
	public List<Projection> findContentProjections(int id);
	
	public void insert(Content content);
	
	public void insert(TopContent topContent);
	
	public void insertTag(Tag tag);
	 
	public void update(Content content);
	 
	public void delete(Content content);
	
	public void delete(TopContent topContent);

	public void deleteTag(Tag tag);
	
}
