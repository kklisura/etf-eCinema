package ba.etf.tim11.eCinema.dao.impl;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.UserDaoImpl;


public class JDBCDaoFactory implements DaoFactory
{
	@Override
	public UserDao getUserDao() {
		return new UserDaoImpl();
	}

}