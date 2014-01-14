package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.LanguageDao;
import ba.etf.tim11.eCinema.dao.mapper.LanguageRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Language;
import ba.etf.tim11.eCinema.utils.DaoUtil;


public class LanguageDaoImpl implements LanguageDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new LanguageRowMapper();
	
	
	public LanguageDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public List<Language> findAll(int offset, int limit) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Languages LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Language find(int id) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Languages WHERE id = ?", 
											 id);
	}
	
	@Override
	public boolean insert(Language language) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO Languages (language, states_id) VALUES (?, ?)",
										  language.getLanguage(),
										  language.getState().getId());

		language.setId(rowId);
		
		return true;
		
	}

	@Override
	public boolean update(Language language) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Languages SET language = ?, states_id = ? WHERE id = ?",
							  language.getLanguage(),
							  language.getState().getId(),
							  language.getId());
		
		return true;
	}

	@Override
	public boolean delete(Language language) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Languages WHERE id = ?", language.getId());
		
		return true;
	}
	
}
