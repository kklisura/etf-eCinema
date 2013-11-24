package ba.etf.tim11.eCinema.resources.filters;

import ba.etf.tim11.eCinema.resources.responses.UnauthorizedException;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;


public class AuthorizationFilter implements ContainerRequestFilter, ResourceFilter
{
	private static final String DEFAULT_USERNAME = "joe.public";
	
	private String resource, privilege;
	
	
	public AuthorizationFilter(String resource, String privilege)
	{
		this.resource = resource;
		this.privilege = privilege;
	}
	
	
	@Override
	public ContainerRequestFilter getRequestFilter() {
		return this;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		return null;
	}

	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) 
	{
		String authToken = getAuthToken(containerRequest);
		
		String username = getUsernameFromAuthToken(authToken), 
			   key = getKeyFromAuthToken(authToken);
		
		if (username == null) {
			username = DEFAULT_USERNAME;
		}
		
		if (!isAllowed(username, key)) {
			throw new UnauthorizedException("You don't have permissions for this resource.");
		}
		
		return containerRequest;
	}
	
	private boolean isAllowed(String username, String sessionKey)
	{
		return false;
	}
	
	
	private String getAuthToken(ContainerRequest containerRequest) 
	{
		String authToken = containerRequest.getHeaderValue("X-Auth");
		if (authToken == null) 
		{
			if (!containerRequest.getCookieNameValueMap().containsKey("auth")) {
				return null;
			}
			
			authToken = containerRequest.getCookieNameValueMap().getFirst("auth");
		}
		
		return authToken;
	}

	
	private static String getUsernameFromAuthToken(String authToken)
	{
		if (authToken == null) {
			return null;
		}
		
		int separatorPosition = authToken.indexOf(':');
		if (separatorPosition == -1) {
			return null;
		}
		
		return authToken.substring(0, separatorPosition);
	}
	
	private static String getKeyFromAuthToken(String authToken)
	{
		if (authToken == null) {
			return null;
		}
		
		int separatorPosition = authToken.indexOf(':');
		if (separatorPosition == -1) {
			return null;
		}
		
		return authToken.substring(separatorPosition + 1);
	}

}
