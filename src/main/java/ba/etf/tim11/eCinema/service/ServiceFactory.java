package ba.etf.tim11.eCinema.service;

public interface ServiceFactory 
{
	public UserService getUserService();

	public LoginService getLoginService();
	
	public SecurityService getSecurityService();
	
	public ContentService getContentService();
	
	public StateService getStateService();
	
}
