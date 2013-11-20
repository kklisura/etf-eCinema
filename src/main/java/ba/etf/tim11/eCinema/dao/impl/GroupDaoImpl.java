package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.mapper.GroupRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class GroupDaoImpl  implements GroupDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new GroupRowMapper();
	
	
	public GroupDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Group> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM Groups", rowMapper);
	}

	@Override
	public Group find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM Groups WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public boolean insert(Group group) throws DaoException {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		return false;
	}

	@Override
	public boolean update(Group group) throws DaoException {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		return false;
	}

	@Override
	public Group findByName(String name) throws DaoException {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		return null;
	}

}
