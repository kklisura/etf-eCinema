package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.models.Privilege;

public class PrivilegeDaoImpl extends BaseDao implements PrivilegeDao
{

	@Override
	public List<Privilege> findAll() 
	{
		List<Privilege> privileges= new ArrayList<Privilege>();
		Connection connection=getConnection();
		
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Privileges");
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				privileges.add((Privilege) map(resultSet));
				
			}
		} catch(SQLException e)
		{
			//TODO(nhuseinovic): Something goes here.
		} finally 
		{
			//TODO(nhuseinovic): Something goes here.
		}
		
		return privileges;
		
	}

	@Override
	public Privilege find(int id) {
		
		Privilege privilege = null;
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Pivileges WHERE id=?");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				privilege=(Privilege) map(resultSet);
			}
		} catch (SQLException e)
		{
			//TODO(nhuseinovic): Something goes here.
		} finally 
		{
			//TODO(nhuseinovic): Something goes here.
		}
		
		return privilege;	
		
	}

	@Override
	public boolean insert(Privilege user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Privilege user) {
		// TODO Auto-generated method stub
		return false;
	}

	
	//----------------Helper
	
	protected final Object map(ResultSet rs) {
		//TODO(nhuseinovic): Something goes here.
		
		return null;
	}
	

}
