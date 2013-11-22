package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Receipt;

public interface ReceiptDao 
{
	public List<Receipt> findAll() throws DaoException;
	
	public Receipt find(int id) throws DaoException;
	
	public Receipt insert(Receipt receipt) throws DaoException;
	
}
