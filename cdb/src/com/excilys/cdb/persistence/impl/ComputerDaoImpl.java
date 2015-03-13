package com.excilys.cdb.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.DaoManager;

/**
 * The Class ComputerDaoImpl.
 */
public enum ComputerDaoImpl implements ComputerDao {
	INSTENSE;

	/** The connection. */
	private Connection connection;

	private ComputerDaoImpl(){
		connection = DaoManager.open();
	}

	public ComputerDao getInstance(){
		return INSTENSE;
	}

	public Connection getConnection()
	{
		return connection;
	}
	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#find(int)
	 */
	@Override
	public Computer find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#create(com.excilys.cdb.model.Computer)
	 */
	@Override
	public Computer create(Computer computer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#update(com.excilys.cdb.model.Computer)
	 */
	@Override
	public Computer update(Computer computer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#delete(com.excilys.cdb.model.Computer)
	 */
	@Override
	public void delete(Computer computer) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#count()
	 */
	@Override
	public int count() {
		String query = "SELECT COUNT(*) AS count FROM computer";
		PreparedStatement counter;
		int count = -1;
		try {
			counter = this.connection.prepareStatement(query);
			ResultSet res = counter.executeQuery();
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
		String query = "SELECT * FROM company";
		List<Computer> computers = new ArrayList<>();
		try {
			ResultSet result = connection.createStatement().executeQuery(query);
			while (result.next()) {
				computers.add(new Computer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
}
