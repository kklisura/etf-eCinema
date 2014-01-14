package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ReceiptDao;
import ba.etf.tim11.eCinema.dao.mapper.ReceiptRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Receipt;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class ReceiptDaoImpl implements ReceiptDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new ReceiptRowMapper();
	
	
	public ReceiptDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}


	@Override
	public List<Receipt> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Receipts LIMIT ?, ?", offset, limit);
	}

	@Override
	public Receipt find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Receipts WHERE id = ?", 
											 id);
	}

	@Override
	public boolean insert(Receipt receipt) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection,
											"INSERT INTO Receipts (totalPrice, discount) VALUES (?, ?)",
											receipt.getTotalPrice(),
											receipt.getDiscount());
		receipt.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Receipt receipt) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,
								"UPDATE Receipts SET totalPrice = ?, discount = ?  WHERE id = ?",
								receipt.getTotalPrice(),
								receipt.getDiscount(),
								receipt.getId());
		return true;
	}

	@Override
	public boolean delete(Receipt receipt) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Receipts WHERE id = ?", receipt.getId());
		
		return true;
	}
	
}
