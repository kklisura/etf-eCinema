package ba.etf.tim11.eCinema.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ba.etf.tim11.eCinema.dao.ContentMarkDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.ContentMarkRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.ContentMark;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class ContentMarkDaoImpl implements ContentMarkDao 
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new ContentMarkRowMapper();
	
	
	public ContentMarkDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<ContentMark> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM ContentMarks LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public ContentMark find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM ContentMarks WHERE id = ?", id);
	}

	@Override
	public boolean insert(ContentMark contentMark) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO ContentMarks (mark, contents_id, useractions_id) VALUES (?, ?, ?, ?)", 
										  contentMark.getMark(),
										  contentMark.getContent().getId(),
										  contentMark.getUserAction().getId());
		
		contentMark.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(ContentMark contentMark) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE ContentMarks SET mark = ? WHERE id = ?", 
							  contentMark.getMark(),
							  contentMark.getId());
		
		return true;
	}

	@Override
	public boolean delete(ContentMark contentMark) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM ContentMarks WHERE id = ?", contentMark.getId());
		
		return true;
	}


	@Override
	public float getContentMark(int contentId) throws DaoException {
		Connection connection = daoFactory.getConnection();
		
		ResultSet rs = DaoUtil.executeQuery(connection, 
											"SELECT AVG(mark) FROM ContentMarks WHERE contents_id = ?", 
											contentId);
		try {
			if (rs.next()) {
				return rs.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public void setContentMark(int userId, int contentId, int mark) throws DaoException {

		Connection connection = daoFactory.getConnection();
		
		try {
			CallableStatement cs = connection.prepareCall("{call register_content_mark(?, ?, ?)}");
		
			cs.setInt(1, userId);
			cs.setInt(2, contentId);
			cs.setInt(3, mark);
			
			cs.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
