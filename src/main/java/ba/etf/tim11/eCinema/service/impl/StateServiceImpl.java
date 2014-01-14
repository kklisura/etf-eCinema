package ba.etf.tim11.eCinema.service.impl;

import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.State;
import ba.etf.tim11.eCinema.service.StateService;


public class StateServiceImpl implements StateService 
{
	private DaoFactory daoFactory;
	private StateDao stateDao;

	
	public StateServiceImpl() 
	{
		daoFactory = JDBCDaoFactory.getInstance();
		stateDao = daoFactory.getStateDao();
	}
	
	@Override
	public List<State> findAll(int offset, int limit) {
		return stateDao.findAll(offset, limit);
	}

	@Override
	public State find(int id) {
		return stateDao.find(id);
	}

	@Override
	public boolean insert(State state) {
		return stateDao.insert(state);
	}

	@Override
	public boolean update(State state) {
		return stateDao.insert(state);
	}

	@Override
	public boolean delete(State state) {
		return stateDao.delete(state);
	}
	
}
