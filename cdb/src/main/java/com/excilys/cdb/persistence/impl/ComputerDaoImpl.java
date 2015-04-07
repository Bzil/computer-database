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
import com.excilys.cdb.util.sort.SortCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoImpl.
 */
public enum ComputerDaoImpl implements ComputerDao {
	
	/** The instance. */
	INSTANCE;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDaoImpl.class);

	/**
	 * Instantiates a new computer dao impl.
	 */
	private ComputerDaoImpl() {
	}

	/**
	 * Gets the single instance of ComputerDaoImpl.
	 *
	 * @return single instance of ComputerDaoImpl
	 */
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
			statement.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't execute select request with id " + id);
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
		}
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#find(java.lang.String, com.excilys.cdb.util.sort.SortCriteria)
	 */
	@Override
	public List<Computer> find(String name, SortCriteria criteria) {
		String correctName = "%".concat(name.trim()).concat("%");
		String req =  "SELECT * FROM computer as c WHERE c.name LIKE ? ";
		if( criteria != null ) {
			req = req.concat(criteria.toString());
		}
		LOGGER.trace("Find computers by name : " + name);
		List<Computer> computers = new ArrayList<>();
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement(req);
			statement.setString(1, correctName);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't execute select request with name : " + name);
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
		}
		return computers;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.ComputerDao#findByCompanyId(int)
	 */
	@Override
	public List<Computer> findByCompanyId(int companyId) {
		LOGGER.trace("Find computers by company id : " + companyId);
		List<Computer> computers = new ArrayList<>();
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM computer as c WHERE c.company_id = ? ");
			statement.setInt(1, companyId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't execute select request with this company id : " + companyId);
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
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
			statement.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't exectute create request of " + computer);
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
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
			statement.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't exceute update request of " + computer);
				computer = null;
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
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
			statement.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't execute delete computer id : " + id);
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
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
			result.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't execute count request");
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
		}
		return count;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll()
	 */
	@Override
	public List<Computer> findAll(SortCriteria criteria) {
		String req = "SELECT * FROM computer";
		if ( criteria != null ) {
			req = req.concat(criteria.toString());
		}
		System.out.println("FIND ALL " + req);
		List<Computer> computers = new ArrayList<>();
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			ResultSet result = connection.createStatement().executeQuery(req);
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
			result.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't find all computer");
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
		}
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll(int, int)
	 */
	@Override
	public List<Computer> findAll(int start, int offset, SortCriteria criteria) {
		StringBuilder req = new StringBuilder("SELECT * FROM computer ");
		if ( criteria != null ) {
			req.append(criteria.toString());
		}
		req.append(" LIMIT ?, ? ");
		System.out.println("FIND ALL OFFSET " + req.toString());
		List<Computer> computers = new ArrayList<>();
		Mapper<Computer> mapper = new ComputerMapper();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DaoManager.INSTANCE.getConnection();
			statement = connection
					.prepareStatement(req.toString());
			statement.setInt(1, start);
			statement.setInt(2, offset);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Computer computer = (Computer) mapper.rowMap(result);
				computer.setCompany(findFromId(computer.getCompany().getId()));
				computers.add(computer);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Can't find all computer between [" + start + "-"
						+ (start + offset) + "]");
				LOGGER.debug("Exception trace : ", e);
			}
		} finally {
			DaoManager.INSTANCE.closeConnection();
		}
		return computers;
	}

	/**
	 * Find from id.
	 *
	 * @param id the id
	 * @return the company
	 */
	private Company findFromId(int id) {
		return CompanyDaoImpl.getInstance().find(id);
	}

}
