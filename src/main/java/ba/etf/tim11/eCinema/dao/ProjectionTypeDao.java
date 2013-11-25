package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.ProjectionType;

public interface ProjectionTypeDao
{
	public List<ProjectionType> findAll() throws DaoException;
	
	public ProjectionType find(int id) throws DaoException;
	
	public ProjectionType find(String type) throws DaoException;
	
	public boolean insert(ProjectionType projectionType) throws DaoException;
	
	public boolean update(ProjectionType projectionType) throws DaoException;
	
	public boolean delete(ProjectionType projectionType) throws DaoException;

}
