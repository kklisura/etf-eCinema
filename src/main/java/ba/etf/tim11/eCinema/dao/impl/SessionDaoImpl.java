package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.SessionDao;
import ba.etf.tim11.eCinema.models.Session;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class SessionDaoImpl implements SessionDao 
{
	private DaoFactory daoFactory;
	
	
	public SessionDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public Session find(String key) throws DaoException 
	{
		Session session = null;
		
		Connection connection = daoFactory.getConnection();
		ResultSet rs = DaoUtil.executeQuery(connection, "SELECT * FROM Sessions WHERE `key` = ?", key);
		
		try 
		{
			if (rs.next()) 
			{
				session = new Session();
				
				session.setId(rs.getInt(1));
				
				User user = new User();
				user.setId(rs.getInt(2));
				session.setUser(user);
				
				session.setKey(rs.getString(3));
				session.setTimestamp(rs.getDate(4));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			throw new DaoException("find failed in SessionDaoImpl. " + e.getMessage());
		}
		
		return session;
	}

	@Override
	public boolean insert(Session session) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "INSERT INTO Sessions(users_id, `key`) VALUES (?, ?)",
							  session.getUser().getId(),
							  session.getKey());
		
		return true;
	}

	@Override
	public boolean delete(Session session) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Sessions WHERE id = ?", session.getId());
		
		return true;
	}

}
