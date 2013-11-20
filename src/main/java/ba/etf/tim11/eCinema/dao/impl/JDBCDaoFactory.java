package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;

import ba.etf.tim11.eCinema.dao.CommentDao;
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.dao.PrivilegeTypeDao;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.UserActionCommentDao;
import ba.etf.tim11.eCinema.dao.UserActionContentDao;
import ba.etf.tim11.eCinema.dao.UserActionDao;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.UserDaoImpl;


public class JDBCDaoFactory implements DaoFactory
{
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDao getCommentDao() {
		return new CommentDaoImpl(this);
	}

	@Override
	public ContentDao getContentDao() {
		return new ContentDaoImpl(this);
	}

	@Override
	public GroupDao getGroupDao() {
		return new GroupDaoImpl(this);
	}

	@Override
	public PrivilegeDao getPrivilegeDao() {
		return new PrivilegeDaoImpl(this);
	}

	@Override
	public PrivilegeTypeDao getPrivilegeTypeDao() {
		return new PrivilegeTypeDaoImpl(this);
	}

	@Override
	public ResourceDao getResourceDao() {
		return new ResourceDaoImpl(this);
	}

	@Override
	public RoleDao getRoleDao() {
		return new RoleDaoImpl(this);
	}

	@Override
	public StateDao getStateDao() {
		return new StateDaoImpl(this);
	}

	@Override
	public UserActionCommentDao getUserActionCommentDao() {
		return new UserActionCommentDaoImpl(this);
	}

	@Override
	public UserActionContentDao getUserActionContentDao() {
		return new UserActionContentDaoImpl(this);
	}

	@Override
	public UserActionDao getUserActionDao() {
		return new UserActionDaoImpl(this);
	}

	@Override
	public UserDao getUserDao() {
		return new UserDaoImpl(this);
	}
	
}