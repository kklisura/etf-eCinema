package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface RowMapper
{
    public Object map(ResultSet rs) throws SQLException;
}