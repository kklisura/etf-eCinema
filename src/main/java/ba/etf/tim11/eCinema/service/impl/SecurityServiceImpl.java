package ba.etf.tim11.eCinema.service.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.PrivilegeDao;
import ba.etf.tim11.eCinema.dao.PrivilegeTypeDao;
import ba.etf.tim11.eCinema.dao.ResourceDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.PrivilegeType;
import ba.etf.tim11.eCinema.models.Resource;
import ba.etf.tim11.eCinema.service.SecurityService;


public class SecurityServiceImpl implements SecurityService 
{
	private DaoFactory daoFactory;
	private PrivilegeTypeDao privilegeTypeDao;
	private PrivilegeDao privilegeDao;
	private ResourceDao resourceDao;

	private Dictionary<String, PrivilegeType> privilegeTypes;
	
	
	public SecurityServiceImpl()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.privilegeTypeDao = daoFactory.getPrivilegeTypeDao();
		this.privilegeDao = daoFactory.getPrivilegeDao();
		this.resourceDao = daoFactory.getResourceDao();
		
		this.privilegeTypes = new Hashtable<>();
		
		retrievePrivilegeTypes();
	}
	
	@Override
	public void registerPrivilegeType(String name)
	{
		if (privilegeTypes.get(name) != null) {
			return;
		}

		PrivilegeType privilegeType = new PrivilegeType();
    	privilegeType.setName(name);
    	privilegeTypeDao.insert(privilegeType);
    	
    	privilegeTypes.put(name, privilegeType);
    }
	
	@Override
	public boolean isAllowed(String username, String resourceName, String privilegeName) 
	{
		PrivilegeType privilegeType = privilegeTypes.get(privilegeName);
		if (privilegeType == null) {
			// TODO(kklisura): Log privilege not found.
			return false;
		}
		
		Resource resource = resourceDao.find(resourceName);
		if (resource == null) {
			// TODO(kklisura): Log resource not found.
			return false;
		}
		
		return privilegeDao.isAllowed(username, resource.getId(), privilegeType.getId());
	}
	
	private void retrievePrivilegeTypes()
	{
		for(PrivilegeType priv : privilegeTypeDao.findAll(0, 999999999)) {
			privilegeTypes.put(priv.getName(), priv);
		}
	}

}
