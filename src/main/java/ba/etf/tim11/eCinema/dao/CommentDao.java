package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Comment;


public interface CommentDao
{
public List<Comment> findAll();
	
	public Comment find(int id);
	
	public boolean insert(Comment comment);
	
	public boolean update(Comment comment);
	
}
