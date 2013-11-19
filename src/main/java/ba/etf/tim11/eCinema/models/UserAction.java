package ba.etf.tim11.eCinema.models;

import java.util.Date;


public class UserAction 
{
	int id;
	User user;
	UserActionType userActionType;
	Date time;
	Date updatedAt;
	Date createdAt;
	
	
	public UserAction(int id, User user, UserActionType userActionType, 
					  Date time, Date updatedAt, Date createdAt)
	{
		this.id = id;
		this.user = user;
		this.userActionType = userActionType;
		this.time = time;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserActionType getUserActionType() {
		return userActionType;
	}
	
	public void setUserActionType(UserActionType userActionType) {
		this.userActionType = userActionType;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
