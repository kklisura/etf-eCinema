package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.GroupDao;
import ba.etf.tim11.eCinema.dao.mapper.GroupRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Group;
import ba.etf.tim11.eCinema.models.Role;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class GroupDaoImpl  implements GroupDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new GroupRowMapper();
	
	
	public GroupDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Group> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Groups LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Group find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Groups WHERE id = ?", id);
	}
	
	@Override
	public Group find(String name) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, rowMapper, "SELECT * FROM Groups WHERE name = ?", name);
	}

	@Override
	public boolean insert(Group group) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO Groups (name, description) VALUES (?, ?)",
										  group.getName(),
										  group.getDescription());
		
		group.setId(rowId);

		for(Role role : group.getRoles()) 
		{
			DaoUtil.executeUpdate(connection, 
				  			  	 "INSERT INTO GroupRoles(roles_id, groups_id) VALUES (?, ?)",
				  			  	 role.getId(),
				  			  	 group.getId());
		}
		
		return true;
	}

	@Override
	public boolean update(Group group) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Groups SET name = ?, description = ? WHERE id = ?",
							  group.getName(),
							  group.getDescription(),
							  group.getId());
		
		DaoUtil.executeUpdate(connection,
							  "DELETE IGNORE FROM GroupRoles WHERE groups_id = ?",
							  group.getId());
		
		for(Role role : group.getRoles()) 
		{
			DaoUtil.executeUpdate(connection, 
					  			  "INSERT INTO GroupRoles(roles_id, groups_id) VALUES (?, ?)",
					  			  role.getId(),
					  			  group.getId());
		}
		
		return true;
	}

	@Override
	public boolean delete(Group group) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Groups WHERE id = ?", group.getId());
		
		return true;
	}

	@Override
	public List<Group> findAllByUser(int userId) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT g.* FROM Groups g, UserGroups ug WHERE ug.groups_id = g.id AND ug.users_id = ?", 
									userId);
	}
	
}
