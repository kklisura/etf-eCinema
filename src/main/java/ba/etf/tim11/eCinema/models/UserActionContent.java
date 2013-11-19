package ba.etf.tim11.eCinema.models;

public class UserActionContent 
{
	int id;
	Content content;
	UserAction userAction;
	
	
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
	
}
