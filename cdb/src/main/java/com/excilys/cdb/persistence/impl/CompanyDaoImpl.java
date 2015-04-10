package com.excilys.cdb.persistence.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public Company find(int id) {
		LOGGER.info("Find company with id " + id);
		String sql = "SELECT * FROM company WHERE id = ?";
		return jdbc.queryForObject(sql, new Object[] { id }, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.CompanyDao#create(com.excilys.cdb.model.Compagny
	 * )
	 */
	@Override
	public Company create(Company company) {
		LOGGER.info("Create company " + company);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update("INSERT INTO company(name) values (?)",
				company.getName(), keyHolder );
		company.setId(keyHolder.getKey().intValue());
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.CompanyDao#update(com.excilys.cdb.model.Compagny
	 * )
	 */
	@Override
	public Company update(Company company) {
		LOGGER.info("Update company " + company);
		jdbc.update("UPDATE company SET name = ? WHERE id = ?",  new Object[] {
				company.getName(),
				company.getId()
		});

		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.CompanyDao#delete(com.excilys.cdb.model.Compagny
	 * )
	 */
	@Override
	public void delete(int id) {
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
			String sql = "SELECT * FROM company";
			return jdbc.query(sql, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll(int, int)
	 */
	@Override
	public List<Company> findAll(int start, int offset) {
		String sql = "SELECT * FROM company LIMIT ?, ? ";
			return jdbc.query(sql, new Object[] { start, offset }, mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#count()
	 */
	@Override
	public int count() {
		String sql =  "SELECT COUNT(*) AS count FROM company";
		return jdbc.queryForObject(sql, Integer.class);

	}

}
