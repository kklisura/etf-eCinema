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
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Comments", rowMapper);
	}
	
	@Override
	public Comment find(int id)  throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Comments WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}
	
	@Override
	public boolean insert(Comment comment) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Comment comment) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
