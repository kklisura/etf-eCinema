package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.models.Group;

public class GroupDaoImpl extends BaseDao implements GroupDao
{

	@Override
	public List<Group> findAll() {
		
		List<Group> groups=new ArrayList<Group>();
		
		Connection connection= getConnection();
		
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Groups");
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				groups.add((Group) map(resultSet));
			}
			
		} catch(SQLException e)
		{
			// TODO(nhuseinovic):NOTE(): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic):NOTE(): Something goes here.
		}
		
		return groups;
		
	}

	@Override
	public Group find(int id) {
		
		Group group=null;
		
		Connection connection=getConnection();
		
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Groups WHERE id=?");
			preparedStatement.setInt(1,id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				group=(Group) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic):NOTE(): Something goes here.
		} finally
		{
			// TODO(nhuseinovic):NOTE(): Something goes here.
		}
		
		return group;
		
	}

	@Override
	public boolean insert(Group group) {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		return false;
	}

	@Override
	public boolean update(Group group) {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		return false;
	}

	@Override
	public Group findByName(String name) {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		return null;
	}

	
	protected final Object map(ResultSet rs) {
		// TODO(nhuseinovic):NOTE(): Something goes here.
		
		return null;
	}

}
