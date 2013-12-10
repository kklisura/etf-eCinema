package ba.etf.tim11.eCinema.service.impl;

import ba.etf.tim11.eCinema.service.LoginService;
import ba.etf.tim11.eCinema.service.SecurityService;

public class ServiceFactory 
{
	public static SecurityService securityService = SecurityServiceImpl.getInstance();
	public static LoginService loginService = LoginServiceImpl.getInstance();
	
	
	private ServiceFactory() {
		
	}
	
	public static SecurityService getSecurityService() {
		return securityService;
	}
	
	public static LoginService getLoginService() {
		return loginService;
	}
	
}
