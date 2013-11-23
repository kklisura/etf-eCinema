package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ProjectionTypeDao;
import ba.etf.tim11.eCinema.dao.mapper.CommentRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.ProjectionType;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class ProjectionTypeDaoImpl implements ProjectionTypeDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new CommentRowMapper();
	
	
	public ProjectionTypeDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<ProjectionType> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeSelectMultipleQuery(connection, "SELECT * FROM ProjectionTypes", rowMapper);
	}

	@Override
	public ProjectionType find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		String query = "SELECT * FROM ProjectionTypes WHERE id = ?";
		
		return DaoUtil.executeSelectWithId(connection, query, id, rowMapper);
	}

	@Override
	public ProjectionType find(String type) throws DaoException 
	{

		return null;
	}

}
