package ba.etf.tim11.eCinema.service.impl;

import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.UserActionDao;
import ba.etf.tim11.eCinema.dao.UserActionTypeDao;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.models.UserAction;
import ba.etf.tim11.eCinema.models.UserActionType;
import ba.etf.tim11.eCinema.service.UserService;


public class UserServiceImpl implements UserService 
{
	private DaoFactory daoFactory;
	private UserDao userDao;
	private StateDao stateDao;
	private UserActionDao userActionDao;
	private UserActionTypeDao userActionTypeDao;
	private GroupDao groupDao;
	private RoleDao roleDao;
	
	
	public UserServiceImpl() 
	{
		daoFactory = JDBCDaoFactory.getInstance();
		userDao = daoFactory.getUserDao();
		stateDao = daoFactory.getStateDao();
		groupDao = daoFactory.getGroupDao();
		roleDao = daoFactory.getRoleDao();
		userActionDao = daoFactory.getUserActionDao();
	}

	@Override
	public User find(int id) 
	{
		User user = userDao.find(id);
		if (user == null) {
			return null;
		}
		
		State state = stateDao.find(user.getState().getId());
		user.setState(state);
		
		return user;
	}

	@Override
	public User getUser(String usernameOrEmail) 
	{
		User user = userDao.find(usernameOrEmail);
		if (user == null) {
			return null;
		}
		
		State state = stateDao.find(user.getState().getId());
		user.setState(state);
		
		return user;
	}

	@Override
	public List<Group> getUserGroups(User user) {
		return groupDao.findAllByUser(user.getId());
	}

	@Override
	public List<Role> getUserRoles(User user) {
		return roleDao.findAllByUser(user.getId());
	}

	@Override
	public List<UserAction> getUserActions(User user, int offset, int limit) 
	{
		List<UserAction> userActions = userActionDao.findAllByUser(user.getId(), offset, limit);
		
		for(UserAction userAction : userActions)
		{
			int typeId = userAction.getUserActionType().getId();
			
			UserActionType actionType = userActionTypeDao.find(typeId);
			userAction.setUserActionType(actionType);
		}
		
		return userActions;
	}

	@Override
	public List<User> findAll(int offset, int limit) {
		return userDao.findAll(offset, limit);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public int numberOfUsers() {
		return userDao.count();
	}
	
}
