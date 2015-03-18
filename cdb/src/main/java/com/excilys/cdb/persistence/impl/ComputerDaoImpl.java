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

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.mapper.Mapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.DaoManager;

/**
 * The Class ComputerDaoImpl.
 */
public enum ComputerDaoImpl implements ComputerDao {
	INSTANCE;

	private ComputerDaoImpl() {
	}

	public ComputerDao getInstance() {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			System.out.println(computer);
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
			if (computer.getCompanyId() != -1) {
				statement.setInt(4, computer.getCompanyId());
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
			e.printStackTrace();
		}
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
			if (computer.getCompanyId() != -1) {
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
			e.printStackTrace();
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
				computers.add((Computer) mapper.rowMap(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}

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
				computers.add((Computer) mapper.rowMap(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
}
