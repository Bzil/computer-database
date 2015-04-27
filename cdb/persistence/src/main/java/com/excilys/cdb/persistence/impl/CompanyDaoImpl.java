package com.excilys.cdb.persistence.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;

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
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#find(int)
	 */
	@Override
	public Company find(final int id) {
		LOGGER.info("Find company with id {}", id);
		return (Company) sessionFactory.getCurrentSession().get(Company.class,
				id);
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
		LOGGER.info("Delete company {}", id);
		final Company company = (Company) sessionFactory.getCurrentSession()
				.get(Company.class, id);
		if (company != null) {
			sessionFactory.getCurrentSession().delete(company);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Company.class)
				.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAll(final int start, final int offset) {
		return sessionFactory.getCurrentSession().createCriteria(Company.class)
				.setFirstResult(start).setMaxResults(offset).list();
	}

}
