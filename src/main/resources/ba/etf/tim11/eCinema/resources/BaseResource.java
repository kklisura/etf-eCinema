package ba.etf.tim11.eCinema.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;


public abstract class BaseResource 
{
	@QueryParam("offset") @DefaultValue("0")
	protected int offset;
	
	@QueryParam("limit") @DefaultValue("30") 
	protected int limit;
}
