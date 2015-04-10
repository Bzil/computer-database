package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.util.mapper.ComputerMapper;
import com.excilys.cdb.util.sort.SortCriteria;

/**
 * The Class ComputerDaoImpl.
 */
// TODO put sql req into static string
// TODO get primary key CRUD @see javadoc
@Repository
public class ComputerDaoImpl implements ComputerDao {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDaoImpl.class);

	/**
	 * Instantiates a new computer dao impl.
	 */
	private ComputerDaoImpl() {
	}

	@Autowired
	private ComputerMapper mapper;

	@Autowired
	private JdbcTemplate jdbc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#find(int)
	 */
	@Override
	public Computer find(int id) {
		LOGGER.info("Find computer " + id);
		String sql = "SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.id = ? ";
		return jdbc.queryForObject(sql, new Object[] { id }, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#find(java.lang.String,
	 * com.excilys.cdb.util.sort.SortCriteria)
	 */
	@Override
	public List<Computer> find(String name, SortCriteria criteria) {
		LOGGER.info("Find computers by name : " + name);
		String correctName = "%".concat(name.trim()).concat("%");
		String sql = "SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.name LIKE ? ";

		if (criteria != null) {
			sql = sql.concat(criteria.toString());
		}
		return jdbc.query(sql, new Object[] { correctName }, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#findByCompanyId(int)
	 */
	@Override
	public List<Computer> findByCompanyId(int companyId) {
		LOGGER.info("Find computers by company id : " + companyId);
		String sql = "SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.company_id = ? ";
		return jdbc.query(sql, new Object[] { companyId }, mapper);
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
		LOGGER.debug("Create computer info");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection
						.prepareStatement(sql.toString(),
								Statement.RETURN_GENERATED_KEYS);
				if (computer.getName().trim().isEmpty()) {
					throw new NullPointerException();
				}
				statement.setString(1, computer.getName().trim());
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
						&& computer.getCompany().getId() > 0) {
					statement.setLong(4, computer.getCompany().getId());
				} else {
					statement.setNull(4, Types.INTEGER);
				}
				return statement;
			}
		};
		jdbc.update(psc, keyHolder);
		computer.setId(keyHolder.getKey().intValue());
		LOGGER.debug("Create computer " + computer);
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
		LOGGER.info("Update computer " + computer);
		jdbc.update(
				"UPDATE computer SET name = ? , introduced = ? , discontinued = ?, company_id = ? WHERE id = ? ",
				new Object[] {
						computer.getName(),
						computer.getIntroduced() != null ? Timestamp
								.valueOf((computer.getIntroduced())) : null,
						computer.getDiscontinued() != null ? Timestamp
								.valueOf((computer.getDiscontinued())) : null,
						computer.getCompany() != null
								&& computer.getCompany().getId() > 0 ? computer
								.getCompany().getId() : null, computer.getId() });
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
		LOGGER.info("Delete computer whit id + " + id);
		jdbc.update("DELETE FROM computer WHERE id = ?", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#deleteByCompanyId(int)
	 */
	@Override
	public void deleteByCompanyId(int companyId) {
		LOGGER.info("Delete computer whit company id + " + companyId);
		jdbc.update("DELETE FROM computer WHERE company_id = ?", companyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#count()
	 */
	@Override
	public int count() {
		String sql = "SELECT COUNT(*) AS count FROM computer";
		return jdbc.queryForObject(sql, Integer.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll()
	 */
	@Override
	public List<Computer> findAll(SortCriteria criteria) {
		String sql = "SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id ";
		if (criteria != null) {
			sql = sql.concat(criteria.toString());
		}
		return jdbc.query(sql, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll(int, int)
	 */
	@Override
	public List<Computer> findAll(int start, int offset, SortCriteria criteria) {
		StringBuffer req = new StringBuffer(
				"SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id ");
		if (criteria != null) {
			req.append(criteria.toString());
		}
		req.append(" LIMIT ?, ? ");
		return jdbc.query(req.toString(), new Object[] { start, offset },
				mapper);
	}

}
