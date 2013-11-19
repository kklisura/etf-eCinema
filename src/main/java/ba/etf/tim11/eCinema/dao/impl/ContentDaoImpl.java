package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.ContentDao;
import ba.etf.tim11.eCinema.models.Content;


public class ContentDaoImpl extends BaseDao implements ContentDao
{

	@Override
	public List<Content> findAll()
	{
		List<Content> contents = new ArrayList<Content>();
		
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Contents");
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				contents.add((Content) map(resultSet));
			}
		} catch(SQLException e)
		{
			// TODO(nhuseinovic) NOTE(): Something goes here.
		
		} finally {
			// TODO(nhuseinovic) NOTE(): Something goes here.
		}
		
		return contents;
	}

	@Override
	public Content find(int id) {
		Content content= null;
		Connection connection=getConnection();
		
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Contents WHERE id=?");
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				content=(Content) map(resultSet);
			}
		} catch(SQLException e)
		{
			// TODO(nhuseinovic) NOTE(): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic) NOTE(): Something goes here.
		}
		
		return content;
	}

	@Override
	public boolean insert(Content content) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Content content) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	// Helper ---------------------------------------------------------------
	
		protected final Object map(ResultSet rs)
		{
			// TODO(nhuseinovic): Need to implement this.
			//
			// User user = new User();
			// user.setId(rs.getInt(1));
			// user.setFirstName(rs.getString(2));
			// ...
			// return user;
			// 
			return null;
		}
	

}
