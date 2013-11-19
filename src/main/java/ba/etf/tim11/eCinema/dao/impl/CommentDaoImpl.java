package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.CommentDao;
import ba.etf.tim11.eCinema.models.Comment;

public class CommentDaoImpl extends BaseDao implements CommentDao 
{

	@Override
	public List<Comment> findAll() {
		
		List<Comment> comments = new ArrayList<Comment>();
		
		Connection connection = getConnection();
		 try 
		    {
		        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comments");
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
	            while(resultSet.next()) {
	                comments.add((Comment) map(resultSet));
	            }

		    } catch (SQLException e ) 
		    {
		    	// NOTE(): Something goes here.

		    } finally {
		    	// NOTE(): Something gors here.

		    }
		 return comments;
		 
	}

	@Override
	public Comment find(int id)
	{
		Comment comment = null;
		
		Connection connection = getConnection();
		try 
	    {
	        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comments WHERE id = ?");
	        
	        preparedStatement.setInt(1, id);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
            if (resultSet.next()) {
                comment = (Comment) map(resultSet);
            }
            
	    } catch (SQLException e ) 
	    {
	    	// NOTE(): Something goes here.

	    } finally {
	    	// NOTE(): Something gors here.

	    }
		
		return comment;
		
	}

	@Override
	public boolean insert(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	// Helper ---------------------------------------------------------------

	protected Object map(ResultSet rs) {
		
				// TODO(nhuseinovic): Need to implement this.
	            //
	            // Coment comment = new Comment();
				// comment.setId(rs.getInt(1));
				// ...
				// return comment;
				// 
		return null;
	}
	

}
