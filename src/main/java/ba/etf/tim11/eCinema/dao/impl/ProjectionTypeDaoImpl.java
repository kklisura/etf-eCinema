package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Connection connection = daoFactory.getConnection();
		
		ProjectionType projectionType = null;
		
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ProjectionTypes WHERE type = ?");
			
			preparedStatement.setString(1, type);
			
		    ResultSet resultSet = preparedStatement.executeQuery();
		        
	        if (resultSet.next()) {
	        	projectionType = (ProjectionType) rowMapper.map(resultSet);
	        }
	        
		} catch (SQLException e) 
		{
			throw new DaoException("find(byProjectionType) failed. " + e.getMessage());
		}
		
		return projectionType;
	}

}
