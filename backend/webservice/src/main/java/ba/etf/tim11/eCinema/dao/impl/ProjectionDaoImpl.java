package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.util.List;

import ba.etf.tim11.eCinema.dao.DaoException;
import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ProjectionDao;
import ba.etf.tim11.eCinema.dao.mapper.CommentRowMapper;
import ba.etf.tim11.eCinema.dao.mapper.RowMapper;
import ba.etf.tim11.eCinema.models.Projection;
import ba.etf.tim11.eCinema.utils.DaoUtil;

public class ProjectionDaoImpl implements ProjectionDao
{
	private DaoFactory daoFactory;
	private static RowMapper rowMapper = new CommentRowMapper();
	
	
	public ProjectionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	
	@Override
	public List<Projection> findAll(int offset, int limit) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQuery(connection, 
									rowMapper, 
									"SELECT * FROM Projections LIMIT ?, ?",
									offset,
									limit);
	}

	@Override
	public Projection find(int id) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		return DaoUtil.executeQueryReturnOne(connection, 
											 rowMapper, 
											 "SELECT * FROM Projections WHERE id = ?", 
											 id);
	}

	@Override
	public boolean insert(Projection projection) throws DaoException 
	{
		
		Connection connection = daoFactory.getConnection();
		
		int rowId = DaoUtil.executeUpdate(connection, 
											"INSERT INTO Projections (time, pricePerSeat, contens_id, cinemaholls_id, projectiontypes_id) VAlUES (?, ?, ?, ?, ?)",
											DaoUtil.utilDate2SqlDatw(projection.getTime()),
											projection.getPricePerSeat(),
											projection.getContent().getId(),
											projection.getCinemaHall().getId(),
											projection.getProjectionType().getId());
		projection.setId(rowId);
		
		return true;
	}

	@Override
	public boolean update(Projection projection) throws DaoException 
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection,
							  "UPDATE Projections SET time = ?, pricePerSeat = ?, contens_id = ?, cinemaholls_id = ?, projectiontypes_id = ? WHERE id = ?",
							  DaoUtil.utilDate2SqlDatw(projection.getTime()),
							  projection.getPricePerSeat(),
							  projection.getContent().getId(),
							  projection.getCinemaHall().getId(),
							  projection.getProjectionType().getId(),
							  projection.getId());
		return true;
	}

	@Override
	public boolean delete(Projection projection) throws DaoException
	{
		Connection connection = daoFactory.getConnection();
		
		DaoUtil.executeUpdate(connection, "DELETE FROM Projections WHERE id = ?", projection.getId());
		
		return true;
	}

}
