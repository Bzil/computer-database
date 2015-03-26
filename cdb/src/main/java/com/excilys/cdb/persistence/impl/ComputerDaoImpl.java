package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.DaoManager;
import com.excilys.cdb.util.mapper.ComputerMapper;
import com.excilys.cdb.util.mapper.Mapper;

/**
 * The Class ComputerDaoImpl.
 */
public enum ComputerDaoImpl implements ComputerDao {
	INSTANCE;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDaoImpl.class);

	private ComputerDaoImpl() {
	}

	public static ComputerDao getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#find(int)
	 */
	@Override
	public Computer find(int id) {
		LOGGER.trace("Find company " + id);
		Computer computer = null;
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM computer WHERE id = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
			}
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't execute select request with id " + id);
			// e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(statement, connection);
		}
		return computer;
	}

	@Override
	public List<Computer> find(String name) {
		String correctName = "%"+name+"%";
		LOGGER.trace("Find computers by name : " + name);
		List<Computer> computers = new ArrayList<>();
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM computer as c WHERE c.name LIKE ?");
			statement.setString(1, correctName);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't execute select request with name : " + name);
			// e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(statement, connection);
		}
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.ComputerDao#create(com.excilys.cdb.model.
	 * Computer)
	 */
	@Override
	public Computer create(Computer computer) {
		LOGGER.trace("Create computer " + computer);
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement(
							"INSERT INTO computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, computer.getName());

			if (computer.getIntroduced() != null) {
				statement.setTimestamp(2,
						Timestamp.valueOf((computer.getIntroduced())));
			} else {
				statement.setNull(2, Types.TIMESTAMP);
			}
			if (computer.getDiscontinued() != null) {
				statement.setTimestamp(3,
						Timestamp.valueOf((computer.getDiscontinued())));
			} else {
				statement.setNull(3, Types.TIMESTAMP);
			}
			if (computer.getCompany() != null
					&& computer.getCompany().getId() != -1) {
				statement.setInt(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, Types.INTEGER);
			}
			statement.executeUpdate();
			// get keys
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				computer.setId(generatedKeys.getInt(1));
			}
			generatedKeys.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't exectute create request of " + computer);
			// e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(statement, connection);
		}
		if (computer.getCompany() != null)
			computer.setCompany(findFromId(computer.getCompany().getId()));

		return computer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.ComputerDao#update(com.excilys.cdb.model.
	 * Computer)
	 */
	@Override
	public Computer update(Computer computer) {
		LOGGER.trace("Update computer " + computer);
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement("UPDATE computer SET name = ? , introduced = ? , discontinued = ?, company_id = ? WHERE id = ? ");
			statement.setString(1, computer.getName());
			if (computer.getIntroduced() != null) {
				statement.setTimestamp(2,
						Timestamp.valueOf(computer.getIntroduced()));
			} else {
				statement.setNull(2, Types.TIMESTAMP);
			}
			if (computer.getDiscontinued() != null) {
				statement.setTimestamp(3,
						Timestamp.valueOf(computer.getDiscontinued()));
			} else {
				statement.setNull(3, Types.TIMESTAMP);
			}
			if (computer.getCompany() != null
					&& computer.getCompany().getId() != -1) {
				statement.setInt(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, Types.INTEGER);
			}
			statement.setInt(5, computer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			computer = null;
			LOGGER.debug("Can't exceute update request of " + computer);
			// e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(statement, connection);
		}
		return computer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.ComputerDao#delete(com.excilys.cdb.model.
	 * Computer)
	 */
	@Override
	public void delete(int id) {
		LOGGER.trace("Delete computer whit id + " + id);
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement("DELETE FROM computer WHERE id = ?");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.debug("Can't execute delete computer id : " + id);
			e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(statement, connection);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#count()
	 */
	@Override
	public int count() {
		int count = -1;
		Connection connection = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			ResultSet result = connection.createStatement().executeQuery(
					"SELECT COUNT(*) AS count FROM computer");
			result.next();
			count = result.getInt("count");
		} catch (SQLException e) {
			LOGGER.debug("Can't execute count request");
			// e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(null, connection);
		}
		return count;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll()
	 */
	@Override
	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<>();
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			ResultSet result = connection.createStatement().executeQuery(
					"SELECT * FROM computer");
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't find all computer");
			// e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(statement, connection);
		}
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll(int, int)
	 */
	@Override
	public List<Computer> findAll(int start, int offset) {
		List<Computer> computers = new ArrayList<>();
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM computer LIMIT ?, ? ");
			statement.setInt(1, start);
			statement.setInt(2, offset);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't find all computer between [" + start + "-"
					+ (start + offset) + "]");
			e.printStackTrace();
		} finally {
			DaoManager.INSTANCE.close(statement, connection);
		}
		return computers;
	}

	private Company findFromId(int id) {
		return CompanyDaoImpl.getInstance().find(id);
	}

}
