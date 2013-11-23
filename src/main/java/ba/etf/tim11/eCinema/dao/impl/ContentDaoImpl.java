package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.ContentRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Content;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class ContentDaoImpl implements ContentDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new ContentRowMapper();
	
	
	public ContentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<Content> findAll() throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Contents");
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
										  "INSERT INTO Contents (title, actors, director, year, length, types_id, fileId) VALUES (?, ?, ?, ?, ?, ?, ?)",
										  content.getTitle(),
										  content.getActors(),
										  content.getDirector(),
										  content.getYear(),
										  content.getLength(),
										  // TODO(kklisura): Check this out.
										  //content.getType().getId(),
										  content.getFile());
		
		content.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Content content) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Contents SET title = ?, actors = ?, director = ?, year = ?, length = ?, types_id = ?, fileId = ? WHERE id = ?",
							  content.getTitle(),
							  content.getActors(),
							  content.getDirector(),
							  content.getYear(),
							  content.getLength(),
							  // TODO(kklisura): Check this out.
							  //content.getType().getId(),
							  content.getFile(),
							  content.getId());
		
		return true;
	}

	@Override
	public boolean delete(Content content) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Contents WHERE id = ?", content.getId());
		
		return true;
	}
	
}
