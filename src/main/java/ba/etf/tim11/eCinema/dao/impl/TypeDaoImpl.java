package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.TypeDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.TypeRowMapper;
import ba.etf.tim11.eCinema.models.Type;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class TypeDaoImpl implements TypeDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new TypeRowMapper();
	
	
	public TypeDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	
	@Override
	public List<Type> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Types LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Type find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		return DaoUtil.executeQueryReturnOne(connection, 
											rowMapper, 
											"SELECT * FROM Types Where id = ?",
											id);
	}

	@Override
	public boolean insert(Type type) throws DaoException
	{ 
		Connection connection = daoFactory.getConnection();
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO Types (type) VALUES(?)",
										  type.getType(),
										  type.getId());
		type.setId(rowId);
		return true;
			
		
	}

	@Override
	public boolean update(Type type) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Types SET type=? WHERE id = ?",
							  type.getType(),
							  type.getId());
		return false;
	}

	@Override
	public boolean delete(Type type) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		DaoUtil.executeUpdate(connection, "DELETE FROM Types WHERE id = ?", type.getId());
		return true;
	}

}
