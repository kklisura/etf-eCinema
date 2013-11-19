package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.UserActionComment;




public interface UserActionCommentDao {
	
	public List<UserActionComment> findAll();
	
	public UserActionComment find(int id);
	
	public boolean insert(UserActionComment userActionComment);
	
	public boolean update(UserActionComment userActionComment);
	
	
	
}
