package ba.etf.tim11.eCinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.etf.tim11.eCinema.dao.BaseDao;
import ba.etf.tim11.eCinema.dao.StateDao;
import ba.etf.tim11.eCinema.models.State;

public class StateDaoImpl extends BaseDao implements StateDao
{

	@Override
	public List<State> findAll() {
		
		List<State> states = new ArrayList<State>();
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM States");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				states.add((State) map(resultSet));
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally 
		{
			// TODO(nhuseinovic): Something goes here.
		}
		return states;
	}

	@Override
	public State find(int id) {
		
		State state = null;
		Connection connection = getConnection();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM States WHERE id=?");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				state= (State) map(resultSet);
			}
		} catch (SQLException e)
		{
			// TODO(nhuseinovic): Something goes here.
		} finally
		{
			// TODO(nhuseinovic): Something goes here.
		}
		
		return state;
	}

	@Override
	public boolean insert(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public State findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State findByShortName(String shortName) {
		// TODO Auto-generated method stub
		return null;
	}

// ------------------- Helper
	
	protected final Object map(ResultSet rs) {
		
		// TODO(nhuseinovic): Something goes here.
		
		return null;
	}

}
