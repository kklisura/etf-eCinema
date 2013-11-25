package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ReservationDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.UserRowMapper;
import ba.etf.tim11.eCinema.models.Reservation;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class ReservationDaoImpl implements ReservationDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new UserRowMapper();
	
	
	public ReservationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Reservation> findAll() throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Reservations");
	}

	@Override
	public Reservation find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Reservations WHERE id = ?", 
											 id);
	}

	@Override
	public boolean insert(Reservation reservation) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
											"INSERT INTO Reservations (projections_id, users_id, receipts_id, reservationtypes_id) VALUES (?, ?, ?, ?)",
											reservation.getProjection().getId(),
											reservation.getUser().getId(),
											reservation.getReceipt().getId(),
											reservation.getReservationType().getId());
		reservation.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Reservation reservation) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
								"UPDATE Reservations SET projections_id = ?, users_id = ?, receipts_id = ?, reservationtypes_id = ? WHERE id = ?",
								reservation.getProjection().getId(),
								reservation.getUser().getId(),
								reservation.getReceipt().getId(),
								reservation.getReservationType().getId(),
								reservation.getId());
		return true;
	}

	@Override
	public boolean delete(Reservation reservation) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Reservations WHERE id = ?", reservation.getId());
		
		return true;
	}

}
