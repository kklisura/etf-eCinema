package ba.etf.tim11.eCinema.service.impl;

import ba.etf.tim11.eCinema.service.ContentService;
import ba.etf.tim11.eCinema.service.LoginService;
import ba.etf.tim11.eCinema.service.SecurityService;
import ba.etf.tim11.eCinema.service.ServiceFactory;
import ba.etf.tim11.eCinema.service.StateService;
import ba.etf.tim11.eCinema.service.UserService;


public class ServiceFactoryImpl implements ServiceFactory 
{
	private static ServiceFactoryImpl instance = null;
	
	private UserService userService;
	private LoginService loginService;
	private SecurityService securityService;
	private ContentService contentService;
	private StateService stateService;
	
	
	private ServiceFactoryImpl() 
	{
		userService = new UserServiceImpl();
		loginService = new LoginServiceImpl();
		securityService = new SecurityServiceImpl();
		contentService = new ContentServiceImpl();
		stateService = new StateServiceImpl();
	}
	
	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactoryImpl();
		}
		return instance;
	}

	
	@Override
	public UserService getUserService() {
		return userService;
	}

	@Override
	public LoginService getLoginService() {
		return loginService;
	}

	@Override
	public SecurityService getSecurityService() {
		return securityService;
	}

	@Override
	public ContentService getContentService() {
		return contentService;
	}

	@Override
	public StateService getStateService() {
		return stateService;
	}
	
}
