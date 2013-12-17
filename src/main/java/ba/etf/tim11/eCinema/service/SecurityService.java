package ba.etf.tim11.eCinema.service;

public interface SecurityService 
{
	public boolean isAllowed(String username, String resource, String privilege);
	
	public void registerPrivilegeType(String name);
}
