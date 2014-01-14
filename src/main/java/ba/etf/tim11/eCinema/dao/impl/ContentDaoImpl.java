package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.ContentRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.models.Tag;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class ContentDaoImpl implements ContentDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new ContentRowMapper();
	
	
	public ContentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<Content> findAll(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Contents LIMIT ?, ?",
									offset,
									limit);
	}
	
	@Override
	public List<Content> findAllByFilter(String filter, int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT c.* FROM Contents c, Tags t, ContentTags ct WHERE t.id = ct.tags_id AND c.id = ct.contents_id AND t.name = ? LIMIT ?, ?",
									filter,
									offset,
									limit);
	}
	
	@Override
	public List<Content> findRecentlyAdded(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Contents ORDER BY updatedAt DESC LIMIT ?, ?",
									offset,
									limit);
	}
	
	@Override
	public List<Content> findBestRated(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT c.* " + 
									"FROM Contents c, ContentMarks cm " +
									"WHERE c.id = cm.contents_id " +
									"GROUP BY c.id " +
									"ORDER BY AVG(cm.mark) DESC " +
									"LIMIT ?, ?",
									offset,
									limit);
	}
	
	@Override
	public List<Content> findTop(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Contents c WHERE top = 1 LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Content find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Contents WHERE id = ?", id);
	}

	@Override
	public boolean insert(Content content) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO Contents (title, actors, director, year, length, description) VALUES (?, ?, ?, ?, ?, ?)",
										  content.getTitle(),
										  content.getActors(),
										  content.getDirector(),
										  content.getYear(),
										  content.getLength(),
										  content.getDescription());
		
		content.setId(rowId);
		
		for(Tag tag : content.getTags()) {
			DaoUtil.executeUpdate(connection, "INSERT INTO ContentTags(contents_id, tags_id) VALUES(?, ?)", content.getId(), tag.getId());
		}

		return true;
	}

	@Override
	public boolean update(Content content) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Contents SET title = ?, actors = ?, director = ?, year = ?, length = ?, description = ? WHERE id = ?",
							  content.getTitle(),
							  content.getActors(),
							  content.getDirector(),
							  content.getYear(),
							  content.getLength(),
							  content.getDescription(),
							  content.getId());
		
		for(Tag tag : content.getTags()) {
			DaoUtil.executeUpdate(connection, "INSERT INTO ContentTags(contents_id, tags_id) VALUES(?, ?)", content.getId(), tag.getId());
		}
		
		return true;
	}

	@Override
	public boolean delete(Content content) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Contents WHERE id = ?", content.getId());
		
		return true;
	}

	@Override
	public List<Content> findInTheaters(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT c.* FROM Contents c, (SELECT * FROM Projections GROUP BY contents_id) p WHERE p.contents_id = c.id ORDER BY p.time DESC LIMIT ?, ?",
									offset,
									limit);
	}
	
}
