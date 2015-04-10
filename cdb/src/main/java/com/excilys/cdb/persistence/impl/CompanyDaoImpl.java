package com.excilys.cdb.persistence.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.DaoManager;
import com.excilys.cdb.util.mapper.CompanyMapper;

/**
 * The Class CompanyDaoImpl.
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	private CompanyDaoImpl() {
	}

	@Autowired
	private DaoManager manager;

	@Autowired
	private CompanyMapper mapper;

	@Autowired
	private JdbcTemplate jdbc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#find(int)
	 */
	@Override
	public Company find(final int id) {
		LOGGER.info("Find company with id " + id);
		final String sql = "SELECT * FROM company WHERE id = ?";
		return jdbc.queryForObject(sql, new Object[] { id }, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.CompanyDao#delete(com.excilys.cdb.model.Compagny
	 * )
	 */
	@Override
	public void delete(final int id) {
		LOGGER.info("Delete company " + id);
		jdbc.update("DELETE FROM computer WHERE company_id=?", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll()
	 */
	@Override
	public List<Company> findAll() {
		final String sql = "SELECT * FROM company";
		return jdbc.query(sql, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll(int, int)
	 */
	@Override
	public List<Company> findAll(final int start, final int offset) {
		final String sql = "SELECT * FROM company LIMIT ?, ? ";
		return jdbc.query(sql, new Object[] { start, offset }, mapper);
	}

}
