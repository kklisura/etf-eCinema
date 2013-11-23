package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.dao.AudioSynchronizationDao;
import ba.etf.tim11.eCinema.dao.CommentDao;
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.LanguageDao;
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.dao.PrivilegeTypeDao;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.SessionDao;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.SubtitleDao;
import ba.etf.tim11.eCinema.dao.TypeDao;
import ba.etf.tim11.eCinema.dao.UserActionCommentDao;
import ba.etf.tim11.eCinema.dao.UserActionContentDao;
import ba.etf.tim11.eCinema.dao.UserActionDao;
import ba.etf.tim11.eCinema.dao.UserActionTypeDao;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.UserDaoImpl;
import ba.etf.tim11.eCinema.utils.DaoConfiguration;


public class JDBCDaoFactory implements DaoFactory
{
	private static JDBCDaoFactory instance = null;
	
	private static Connection connection = null;
	
	
	private JDBCDaoFactory()
	{
		DaoConfiguration cfg = DaoConfiguration.getInstance();
		
		String url = null, 
			   user = null, 
			   password = null;
		
		try 
		{
			url = cfg.getEntry("db.url", true);
	        user = cfg.getEntry("db.username", true);
	        password = cfg.getEntry("db.password", true);
		} catch (Exception e) {
			e.printStackTrace();
		}

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
        	throw new DaoException("JDBCDaoFactory failed " + ex.getMessage());
        }
	}
	
	public static DaoFactory getInstance() {
		if (instance == null) {
			instance = new JDBCDaoFactory();
		}
		return instance;
	}
	

	@Override
	public Connection getConnection() {
		return connection;
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
	public UserActionTypeDao getUserActionTypeDao() {
		return new UserActionTypeDaoImpl(this);
	}

	@Override
	public UserDao getUserDao() {
		return new UserDaoImpl(this);
	}

	@Override
	public AudioSynchronizationDao getAudioSynchronizationDao() {
		return new AudioSynchronizationDaoImpl(this);
	}

	@Override
	public LanguageDao getLanguageDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubtitleDao getSubtitleDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDao getTypeDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public SessionDao getSessionDao() {
		return new SessionDaoImpl(this);
	}
	
}