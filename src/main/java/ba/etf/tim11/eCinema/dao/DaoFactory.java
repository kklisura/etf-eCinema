package ba.etf.tim11.eCinema.dao;

public interface DaoFactory 
{	
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
    
    public abstract UserDao getUserDao();
}