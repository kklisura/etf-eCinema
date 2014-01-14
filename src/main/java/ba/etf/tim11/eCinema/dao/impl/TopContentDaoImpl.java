package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.TopContentDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.TopContentRowMapper;
import ba.etf.tim11.eCinema.models.TopContent;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class TopContentDaoImpl implements TopContentDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new TopContentRowMapper();
	
	
	public TopContentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	
	@Override
	public List<TopContent> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM TopContents LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public TopContent find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											rowMapper, 
											"SELECT * FROM TopContents WHERE id = ?",
											id);
	}
	
	@Override
	public boolean insert(TopContent topContent) throws DaoException
	{ 
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO TopContents (title, shortDescription, contents_id) VALUES(?, ?, ?)",
										  topContent.getTitle(),
										  topContent.getShortDescription(),
										  topContent.getContent().getId());
		
		topContent.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(TopContent topContent) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE TopContents SET title = ?, shortDescription = ? WHERE id = ?",
							  topContent.getTitle(),
							  topContent.getShortDescription(),
							  topContent.getId());
		
		return true;
	}

	@Override
	public boolean delete(TopContent topContent) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM TopContents WHERE id = ?", topContent.getId());
		
		return true;
	}
	
}
