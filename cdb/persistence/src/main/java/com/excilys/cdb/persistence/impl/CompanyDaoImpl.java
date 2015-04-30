package com.excilys.cdb.persistence.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.dto.CompanyJPA;

/**
 * The Class CompanyDaoImpl.
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	private CompanyDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.CompanyDao#find(int)
	 */
	@Override
	public Company find(final int id) {
		LOGGER.info("Find company with id {}", id);
		return CompanyJPA.from((CompanyJPA) sessionFactory.getCurrentSession().get(CompanyJPA.class, id));
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
		final CompanyJPA company = (CompanyJPA) sessionFactory.getCurrentSession().get(CompanyJPA.class, id);
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
		final List<CompanyJPA> list = sessionFactory.getCurrentSession().createCriteria(CompanyJPA.class).list();
		return list.stream().map(CompanyJPA::from).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAll(final int start, final int offset) {
		final List<CompanyJPA> list = sessionFactory.getCurrentSession().createCriteria(CompanyJPA.class)
				.setFirstResult(start).setMaxResults(offset).list();
		return list.stream().map(CompanyJPA::from).collect(Collectors.toList());
	}

}
