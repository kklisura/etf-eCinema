package ba.etf.tim11.eCinema.models;

import java.util.Date;


public class UserActionComment {
	Comment comment;
	UserAction userAction;
	Date updatedAt;
	Date createdAt;
	public UserActionComment(Comment comment, UserAction userAction,
			Date updatedAt, Date createdAt) {
		this.comment = comment;
		this.userAction = userAction;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
