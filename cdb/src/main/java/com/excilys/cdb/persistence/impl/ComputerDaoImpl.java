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

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.mapper.Mapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.DaoManager;

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
		Computer computer = null;
		Mapper<Computer> mapper = new ComputerMapper();
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM computer WHERE id = ?");) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
			}
		} catch (SQLException e) {
			LOGGER.debug("Can't execute select request with id " + id);
			// e.printStackTrace();
		}
		return computer;
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
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(
								"INSERT INTO computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);) {
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

		} catch (SQLException e) {
			LOGGER.debug("Can't exectute create request of " + computer);
			// e.printStackTrace();
		}
		if( computer.getCompany() != null )
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
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE computer SET name = ? , introduced = ? , discontinued = ?, company_id = ? WHERE id = ? ");) {
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
	public void delete(Computer computer) {
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM computer WHERE id = ?");) {
			statement.setInt(1, computer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.debug("Can't execute delete request of " + computer);
			e.printStackTrace();
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
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				ResultSet res = connection.createStatement().executeQuery(
						"SELECT COUNT(*) AS count FROM computer");) {
			res.next();
			count = res.getInt("count");
		} catch (SQLException e) {
			LOGGER.debug("Can't execute count request");
			// e.printStackTrace();
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
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				ResultSet result = connection.createStatement().executeQuery(
						"SELECT * FROM computer");) {
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
		} catch (SQLException e) {
			LOGGER.debug("Can't find all computer");
			// e.printStackTrace();
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
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM computer LIMIT ?, ? ");) {
			statement.setInt(1, start);
			statement.setInt(2, offset);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
		} catch (SQLException e) {
			LOGGER.debug("Can't find all computer between [" + start + "-"
					+ (start + offset) + "]");
			e.printStackTrace();
		}
		return computers;
	}

	private Company findFromId(int id) {
		return CompanyDaoImpl.getInstance().find(id);
	}
}
