package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.CommentDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.CommentRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Comment;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class CommentDaoImpl implements CommentDao 
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new CommentRowMapper();
	
	
	public CommentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<Comment> findAll() throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Comments");
	}
	
	@Override
	public Comment find(int id)  throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Comments WHERE id = ?", 
											 id);
	}
	
	@Override
	public boolean insert(Comment comment) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO Comments (title, text, contents_id, users_id) VALUES (?, ?, ?, ?)",
										  comment.getTitle(),
										  comment.getText(),
										  comment.getContent().getId(),
										  comment.getUser().getId());

		comment.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Comment comment) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Comments SET title = ?, text = ?, contents_id = ?, users_id = ? WHERE id = ?",
							  comment.getTitle(),
							  comment.getText(),
							  comment.getContent().getId(),
							  comment.getUser().getId(),
							  comment.getId());
		
		return false;
	}


	@Override
	public boolean delete(Comment comment) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Comments WHERE id = ?", comment.getId());
		
		return true;
	}
	
}
