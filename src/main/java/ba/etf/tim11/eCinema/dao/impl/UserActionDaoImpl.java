package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserActionDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserActionRowMapper;
import ba.etf.tim11.eCinema.models.UserAction;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class UserActionDaoImpl implements UserActionDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserActionRowMapper();
	
	
	public UserActionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<UserAction> findAll(int offset, int limit)
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM UserActions LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public UserAction find(int id) 
	{
		Connection connection = daoFactory.getConnection();

		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM UserActions WHERE id = ?", id);
	}
	
	@Override
	public boolean insert(UserAction userAction) 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO UserActions (users_id, useractiontypes_id, time) VALUES (?, ?, ?)",
										  userAction.getUser().getId(),
										  userAction.getUserActionType().getId(),
										  // TODO(kklisura): Time!?
										  userAction.getTime());
		
		userAction.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(UserAction userAction)
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE UserActions SET users_id = ?, useractiontypes_id = ?, time = ? WHERE id = ?",
							  userAction.getUser().getId(),
							  userAction.getUserActionType().getId(),
							  // TODO(kklisura): Time!?
							  userAction.getTime(),
							  userAction.getId());
		
		return true;
	}

	@Override
	public boolean delete(UserAction userAction) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM UserActions WHERE id = ?", userAction.getId());
		
		return true;
	}

}
