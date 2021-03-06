package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.CinemaDao;
import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.mapper.CinemaRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Cinema;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class CinemaDaoImpl implements CinemaDao
{	
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new CinemaRowMapper();
	
	
	public CinemaDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Cinema> findAll(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Cinemas LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Cinema find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Cinemas WHERE id = ?", 
											 id);
	}

	@Override
	public boolean insert(Cinema cinema) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
										  "INSERT INTO Cinemas (name, address) VALUES (?, ?)",
										  cinema.getName(),
										  cinema.getAddress());
		
		cinema.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Cinema cinema) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,
							  "UPDATE Cinemas SET name = ?, address = ? WHERE id = ?",
							  cinema.getName(),
							  cinema.getAddress(),
							  cinema.getId());
		
		return true;
	}

	@Override
	public boolean delete(Cinema cinema) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Cinemas WHERE id = ?", cinema.getId());
		
		return true;
	}

}
