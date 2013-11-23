package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.UserDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserRowMapper;
import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class UserDaoImpl implements UserDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserRowMapper();
	
	
	public UserDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<User> findAll() throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Users");
	}	

	@Override
	public User find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Users WHERE id = ?", 
											 id);
	}
	
	@Override
	public User find(String username) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Users WHERE username = ?", 
											 username);
	}
	

	@Override
	public boolean insert(User user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO Users (lastName, firstName, username, email, phone, address, dateOfBirth, placeOfBirth, states_id, password, salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
										  user.getLastName(),
										  user.getFirstName(),
										  user.getUsername(),
										  user.getEmail(),
										  user.getPhone(),
										  user.getAddress(),
										  DaoUtil.utilDate2SqlDatw(user.getDateOfBirth()),
										  user.getPlaceOfBirth(),
										  user.getState().getId(),
										  user.getPassword(),
										  user.getSalt());
		
		user.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(User user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Users SET lastName = ?, firstName = ?, username = ?, email = ?, phone = ?, address = ?, dateOfBirth = ?, placeOfBirth = ?, states_id = ?, password = ?, salt = ? WHERE id = ?",
							  user.getLastName(),
							  user.getFirstName(),
							  user.getUsername(),
							  user.getEmail(),
							  user.getPhone(),
							  user.getAddress(),
							  DaoUtil.utilDate2SqlDatw(user.getDateOfBirth()),
							  user.getPlaceOfBirth(),
							  user.getState().getId(),
							  user.getPassword(),
							  user.getSalt(),
							  user.getId());
		
		return true;
	}
	
	@Override
	public boolean delete(User user) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Users WHERE id = ?", user.getId());
		
		return true;
	}

}
