package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.RoleDao;
import ba.etf.tim11.eCinema.models.Role;

public class RoleDaoImpl extends BaseDao implements RoleDao
{
	
	@Override
	public List<Role> findAll() {
		
		List<Role>roles = new ArrayList<Role>();
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Roles");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				roles.add((Role) map(resultSet));
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
		return roles;
	}

	@Override
	public Role find(int id) {
		
		Role role = null;
		Connection connection = getConnection();
		
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Roles WHERE id=?");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				role = (Role) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic): Something goes here.
		}
		return role;
	}

	@Override
	public boolean insert(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// -------------------------- Helper

	protected final Object map(ResultSet rs) {
		// TODO(nhuseinovic): Something goes here.
		return null;
	}

}
