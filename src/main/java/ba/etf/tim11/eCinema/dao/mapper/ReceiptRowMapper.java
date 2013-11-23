package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ba.etf.tim11.eCinema.models.Receipt;

public class ReceiptRowMapper implements RowMapper 
{

	@Override
	public Object map(ResultSet rs) throws SQLException 
	{
		Receipt receipt = new Receipt();
		
		receipt.setId(rs.getInt(1));
		receipt.setTotalPrice(rs.getBigDecimal(2));
		receipt.setDiscount(rs.getBigDecimal(3));
		receipt.setUpdatedAt(rs.getDate(4));
		receipt.setCretedAt(rs.getDate(5));
		
		return receipt;
	}

}
