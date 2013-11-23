package ba.etf.tim11.eCinema.dao;

import java.sql.Connection;


public interface DaoFactory 
{
	public abstract Connection getConnection();
	
	
	public abstract CommentDao getCommentDao();
	
	public abstract ContentDao getContentDao();
	
	public abstract GroupDao getGroupDao();
    
	public abstract PrivilegeDao getPrivilegeDao();
    
	public abstract PrivilegeTypeDao getPrivilegeTypeDao();
    
    public abstract ResourceDao getResourceDao();
    
    public abstract RoleDao getRoleDao();
    
    public abstract StateDao getStateDao();
    
    public abstract UserActionCommentDao getUserActionCommentDao();
    
    public abstract UserActionContentDao getUserActionContentDao();
    
    public abstract UserActionDao getUserActionDao();
    
    public abstract UserActionTypeDao getUserActionTypeDao();
    
    public abstract UserDao getUserDao();
    
    public abstract SessionDao getSessionDao();
}