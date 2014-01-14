package ba.etf.tim11.eCinema.models;

import java.util.Date;


public class UserActionContent 
{
	private int id;
	
	private Content content;
	private UserAction userAction;
	
	private Date updatedAt, createdAt;
	
	
	public UserActionContent() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content content) {
		this.content = content;
	}
	
	public UserAction getUserAction() {
		return userAction;
	}
	
	public void setUserAction(UserAction userAction) {
		this.userAction = userAction;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatdAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
