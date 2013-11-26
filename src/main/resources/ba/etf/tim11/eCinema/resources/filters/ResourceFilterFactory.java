package ba.etf.tim11.eCinema.resources.filters;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ext.Provider;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Resource;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.service.SecurityService;
import ba.etf.tim11.eCinema.service.impl.ServiceFactory;

import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;


@Provider
public class ResourceFilterFactory extends RolesAllowedResourceFilterFactory 
{
	private static SecurityService securityService = ServiceFactory.getSecurityService();
	
	private ResourceDao resourceDao;
	
	
	public ResourceFilterFactory()
	{
		DaoFactory daoFactory = JDBCDaoFactory.getInstance();
		
		this.resourceDao = daoFactory.getResourceDao();
	}

	
    @Override
    public List<ResourceFilter> create(AbstractMethod am) 
    {
        List<ResourceFilter> filters = super.create(am);
        if (filters == null) {
            filters = new ArrayList<ResourceFilter>();
        }
        
        if (am.getResource() != null && am.getResource().getPath() != null && am.isAnnotationPresent(Privilege.class)) 
        {
        	String resource = am.getResource().getPath().getValue();
        	String privilege = am.getAnnotation(Privilege.class).value();
        	
        	registerResource(resource);
        	securityService.registerPrivilegeType(privilege);
        	
        	filters = new ArrayList<ResourceFilter>(filters);
          	filters.add(0, new AuthorizationFilter(resource, privilege));
        }
        
        return filters;
    }
    
    
    private void registerResource(String name)
    {
    	if (resourceDao.find(name) == null) 
    	{
    		Resource resource = new Resource();
    		
    		resource.setName(capitalize(name));
    		// TODO(kklisura): Add description here.
    		resource.setDescription("");
    		
    		resourceDao.insert(resource);
    	}
    }
    
    private static String capitalize(String line) {
    	return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
