package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserActionCommentDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserActionCommentRowMapper;
import ba.etf.tim11.eCinema.models.UserActionComment;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class UserActionCommentDaoImpl implements UserActionCommentDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserActionCommentRowMapper();
	
	
	public UserActionCommentDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<UserActionComment> findAll(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM UserActionComments LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public UserActionComment find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM UserActionComments WHERE id = ?", id);
	}

	@Override
	public boolean insert(UserActionComment userActionComment) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO UserActionComments (comments_id, useractions_id) VALUES (?, ?)",
										  userActionComment.getComment().getId(),
										  userActionComment.getUserAction().getId());
		
		userActionComment.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(UserActionComment userActionComment) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE UserActionComments SET comments_id = ?, useractions_id = ? WHERE id = ?",
							  userActionComment.getComment().getId(),
							  userActionComment.getUserAction().getId(),
							  userActionComment.getId());
		
		return true;
	}

	@Override
	public boolean delete(UserActionComment userActionComment) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM UserActionComments WHERE id = ?", userActionComment.getId());
		
		return true;
	}

}
