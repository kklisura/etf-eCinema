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
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM ProjectionType");
	}


	@Override
	public ProjectionType find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM ProjectionType WHERE id = ?", 
											 id);
	}


	@Override
	public ProjectionType find(String type) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM ProjectionType WHERE type = ?", 
											 type);
	}


	@Override
	public boolean insert(ProjectionType projectionType) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
											"INSERT INTO type VAlUES ?",
											projectionType.getType());
		projectionType.setId(rowId);
		
		return true;
	}


	@Override
	public boolean update(ProjectionType projectionType) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
								"UPDATE type = ? WHERE id = ?",
								projectionType.getType(),
								projectionType.getId());
		return true;
	}


	@Override
	public boolean delete(ProjectionType projectionType) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM ProjectionType WHERE id = ?", projectionType.getId());

		return true;
	}
}
