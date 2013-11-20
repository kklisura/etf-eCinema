package ba.etf.tim11.eCinema.dao.mapper;

import java.sql.ResultSet;


public interface RowMapper
{
    public Object map(ResultSet rs);
}