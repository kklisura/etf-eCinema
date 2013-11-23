package ba.etf.tim11.eCinema.resources.responses;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


public class ResourceNotFoundException extends WebApplicationException 
{	
	private static int HttpStatusCode = 404;
	
	private static final long serialVersionUID = 1L;

	
	public ResourceNotFoundException(String message, String moreInfo) 
	{
        super(javax.ws.rs.core.Response.status(HttpStatusCode)
        	  .entity(buildResponse(message, moreInfo))
        	  .type(MediaType.APPLICATION_JSON)
        	  .build());
    }
	
	public ResourceNotFoundException(String message) 
	{
        super(javax.ws.rs.core.Response.status(HttpStatusCode)
        	  .entity(buildResponse(message, null))
        	  .type(MediaType.APPLICATION_JSON)
        	  .build());
    }
	
	
	private static Response buildResponse(String message, String moreInfo) {
		return new Response(HttpStatusCode, message, moreInfo);
	}
	
}

