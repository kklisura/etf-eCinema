package ba.etf.tim11.eCinema.service.impl;

import java.util.List;

import ba.etf.tim11.eCinema.dao.CinemaHallDao;
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ProjectionDao;
import ba.etf.tim11.eCinema.dao.ProjectionTypeDao;
import ba.etf.tim11.eCinema.dao.TagDao;
import ba.etf.tim11.eCinema.dao.TopContentDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.models.Tag;
import ba.etf.tim11.eCinema.models.TopContent;
import ba.etf.tim11.eCinema.service.ContentService;


public class ContentServiceImpl implements ContentService
{
	private DaoFactory daoFactory;
	private ContentDao contentDao;
	private TagDao tagDao;
	private TopContentDao topContentDao;
	private ProjectionDao projectionDao;
	private CinemaHallDao cinemaHallDao;
	private ProjectionTypeDao projectionTypeDao;
	
	
	public ContentServiceImpl() 
	{
		daoFactory = JDBCDaoFactory.getInstance();
		
	    contentDao = daoFactory.getContentDao();
	    tagDao = daoFactory.getTagDao();
	    topContentDao = daoFactory.getTopContentDao();	
	    projectionDao = daoFactory.getProjectionDao();
	    cinemaHallDao = daoFactory.getCinemaHallDao();
	    projectionTypeDao = daoFactory.getProjectionTypeDao();
	}
	
	@Override
	public List<Content> findAll(int offset, int limit) {
		return fillWithTags(contentDao.findAll(offset, limit));
	}

	@Override
	public List<Content> findRecentlyAdded(int offset, int limit) {
		return fillWithTags(contentDao.findRecentlyAdded(offset, limit));
	}
	
	@Override
	public List<Content> findBestRated(int offset, int limit) {
		return fillWithTags(contentDao.findBestRated(offset, limit));
	}

	@Override
	public List<TopContent> findTop(int offset, int limit) {
		return topContentDao.findAll(offset, limit);
	}
	
	@Override
	public List<Content> findAllByFilter(String filter, int offset, int limit) {
		return fillWithTags(contentDao.findAllByFilter(filter, offset, limit));
	}

	@Override
	public Content find(int id) 
	{
		Content content = contentDao.find(id);
		if (content == null) {
			return content;
		}
		
		fillWithTag(content);
		
		return content;
	}

	@Override
	public void insert(Content content) {
		contentDao.insert(content);
	}

	@Override
	public void update(Content content) 
	{
		tagDao.deleteByContent(content.getId());
		contentDao.update(content);		
	}

	@Override
	public void delete(Content content) 
	{
		tagDao.deleteByContent(content.getId());
		contentDao.delete(content);
	}

	@Override
	public List<Tag> findAllTags(int offset, int limit) {
		return tagDao.findAll(offset, limit);
	}

	@Override
	public Tag findTag(int tagId) {
		return tagDao.find(tagId);
	}

	@Override
	public void insertTag(Tag tag) {
		tagDao.insert(tag);
	}

	@Override
	public void deleteTag(Tag tag) {
		tagDao.delete(tag);
	}

	@Override
	public void insert(TopContent topContent) {
		topContentDao.insert(topContent);
	}

	@Override
	public TopContent findTop(int id) {
		return topContentDao.find(id);
	}

	@Override
	public void delete(TopContent topContent) {
		topContentDao.delete(topContent);
	}
	
	
	private Content fillWithTag(Content content) {
		content.setTags(tagDao.findByContent(content.getId()));
		return content;
	}
	
	private List<Content> fillWithTags(List<Content> contents) {
		for(Content content : contents) {
			fillWithTag(content);
		}
		return contents;
	}

	@Override
	public List<Content> findInTheaters(int offset, int limit) {
		return fillWithTags(contentDao.findInTheaters(offset, limit));
	}

	@Override
	public List<Projection> findContentProjections(int id) {
		List<Projection> projections = projectionDao.findAllByContent(id, 0, 9999);
			
		for(Projection p : projections)
		{
			p.setCinemaHall(cinemaHallDao.find(p.getCinemaHall().getId()));
			p.setProjectionType(projectionTypeDao.find(p.getProjectionType().getId()));
		}
		
		return projections;
	}
	
	
}
