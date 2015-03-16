package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.DaoManager;

/**
 * The Class ComputerDaoImpl.
 */
public enum ComputerDaoImpl implements ComputerDao {
	INSTENSE;

	private final Map<String, PreparedStatement> queries = new HashMap<>();
	
	/** The connection. */
	private Connection connection;

	private ComputerDaoImpl(){
		connection = DaoManager.open();
		prepareQueries();
	}

	public ComputerDao getInstance(){
		return INSTENSE;
	}

	public Connection getConnection()
	{
		return connection;
	}
	
	private void prepareQueries() {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM computer WHERE id = ?");
			queries.put("find", statement);
			statement =  connection.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			queries.put("create", statement);
			statement = connection.prepareStatement("UPDATE computer SET name = ? , introduced = ? , discontinued = ?, company_id = ? WHERE id = ? ");
			queries.put("update", statement);
			statement = connection.prepareStatement("DELETE FROM computer WHERE id = ?");
			queries.put("delete", statement);
			statement = connection.prepareStatement("SELECT * FROM computer");
			queries.put("findAll", statement);
			statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM computer");
			queries.put("count", statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#find(int)
	 */
	@Override
	public Computer find(int id) {
		Computer computer = null;
		try {
			PreparedStatement statement = queries.get("find");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.first()){
				computer = new Computer(id,result.getString("name"),result.getTimestamp("introduced"),result.getTimestamp("discontinued"),result.getInt("company_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#create(com.excilys.cdb.model.Computer)
	 */
	@Override
	public Computer create(Computer computer) {
		Computer ojb = null;
		try {
			PreparedStatement statement = queries.get("create"); 
			statement.setString(1, computer.getName());

			if(computer.getIntroduced()!=null){
				statement.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
			} else {
				statement.setNull(2, Types.TIMESTAMP);
			}
			if(computer.getDiscontinued()!=null){
				statement.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
			} else {
				statement.setNull(3, Types.TIMESTAMP);
			}
			if(computer.getCompanyId() != -1){
				statement.setInt(4, computer.getCompanyId());
			} else {
				statement.setNull(4, Types.INTEGER);
			}
			statement.executeUpdate();
			// get keys
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				ojb = this.find(generatedKeys.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ojb;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#update(com.excilys.cdb.model.Computer)
	 */
	@Override
	public Computer update(Computer computer) {
		try {
			PreparedStatement statement = queries.get("update");
			statement.setString(1, computer.getName());
			if(computer.getIntroduced()!=null){
				statement.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
			} else {
				statement.setNull(2, Types.TIMESTAMP);
			}
			if(computer.getDiscontinued()!=null){
				statement.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
			} else {
				statement.setNull(3, Types.TIMESTAMP);
			}
			if(computer.getCompanyId() != -1){
				statement.setInt(4, computer.getCompanyId());
			} else {
				statement.setNull(4, Types.INTEGER);
			}
			statement.setInt(5, computer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.find(computer.getId());
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#delete(com.excilys.cdb.model.Computer)
	 */
	@Override
	public void delete(Computer computer) {
		try {
			PreparedStatement statement = queries.get("delete");
			statement.setInt(1, computer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#count()
	 */
	@Override
	public int count() {
		int count = -1;
		try {
			ResultSet res = queries.get("count").executeQuery();
			res.next();
			count = res.getInt("count");
		} catch(SQLException e){ 
			e.printStackTrace(); 
		}
		return count;

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll()
	 */
	@Override
	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<>();
		try {
			ResultSet result = queries.get("findAll").executeQuery();
			while (result.next()) {
				computers.add(new Computer(result.getInt("id"),result.getString("name"),result.getTimestamp("introduced"),result.getTimestamp("discontinued"),result.getInt("company_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
}
