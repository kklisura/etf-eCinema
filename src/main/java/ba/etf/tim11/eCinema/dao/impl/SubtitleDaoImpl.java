package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.SubtitleDao;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.dao.mapper.SubtitleRowMapper;
import ba.etf.tim11.eCinema.models.Subtitle;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class SubtitleDaoImpl implements SubtitleDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper= new SubtitleRowMapper();
	
	public SubtitleDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Subtitle> findAll() throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		return DaoUtil.executeQuery(connection, rowMapper, "SELECT * FROM Subtitles");
		
	}

	@Override
	public Subtitle find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		return DaoUtil.executeQueryReturnOne(connection,
									rowMapper,
									"SELECT * FROM Subtitles WHERE id=? ",
									id);
		
	}

	@Override
	public boolean insert(Subtitle subtitle) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
										  "INSERT INTO Subtitles (languages_id, contents_id, fileId) VALUES (?, ?, ?)",
										  subtitle.getLanguage().getId(),
										  subtitle.getContent().getId(),
										  subtitle.getFileId());

		subtitle.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Subtitle subtitle) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, 
							  "UPDATE Subtitiles SET languages_id = ?, contents_id = ?, fileId = ? WHERE id = ?",
							  subtitle.getLanguage().getId(),
							  subtitle.getContent().getId(),
							  subtitle.getFileId(),
							  subtitle.getId());
		
		return false;
	}

	@Override
	public boolean delete(Subtitle subtitle) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Subtitles WHERE id = ?", subtitle.getId());
		
		return true;
	}

}
