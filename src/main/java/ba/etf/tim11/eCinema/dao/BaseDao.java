package ba.etf.tim11.eCinema.dao;

import java.sql.Connection;
import java.sql.ResultSet;


public abstract class BaseDao 
{
	private Connection connection;
	
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
	// NOTE(kklisura): Throws exception
	protected abstract Object map(ResultSet rs);
}
