package ba.etf.tim11.eCinema.dao;

import java.util.List;

import ba.etf.tim11.eCinema.models.Language;


public interface LanguageDao
{
	public List<Language> findAll(int offset, int limit) throws DaoException;
	
	public Language find(int id) throws DaoException;
	
	public boolean insert(Language language) throws DaoException;
	
	public boolean update (Language language) throws DaoException;
	
	public boolean delete(Language language) throws DaoException;

}
