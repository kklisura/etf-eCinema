package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.models.Resource;

public class ResourceDaoImpl extends BaseDao implements ResourceDao
{

	@Override
	public List<Resource> findAll() {
		List<Resource> resources = new ArrayList<Resource>();
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Resources");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				resources.add((Resource) map(resultSet));
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
		return resources;
	}

	@Override
	public Resource find(int id) {
		
		Resource resource = null;
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Resources WHERE id=?");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				resource = (Resource) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic); Something goes here.
		}
		
		return resource;
	}

	@Override
	public boolean insert(Resource user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Resource user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Resource findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// --------------- Helper
	protected final Object map(ResultSet rs) {
		// TODO(nhuseinovic): Something goes here.
		
		return null;
	}

}
