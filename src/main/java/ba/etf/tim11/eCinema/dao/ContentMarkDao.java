package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.ContentMark;


public interface ContentMarkDao 
{
	public List<ContentMark> findAll(int offset, int limit) throws DaoException;
	
	public ContentMark find(int id) throws DaoException;
	
	public boolean insert(ContentMark contentMark) throws DaoException;
	
	public boolean update(ContentMark contentMark) throws DaoException;
	
	public boolean delete(ContentMark contentMark) throws DaoException;
	
	public float getContentMark(int contentId) throws DaoException;
	
	public void setContentMark(int userId, int contentId, int mark) throws DaoException;
}
