package com.excilys.cdb.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.sort.SortCriteria;
import com.excilys.cdb.sort.SortDirection;

/**
 * The Class ComputerDaoImpl.
 */
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
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#find(int)
	 */
	@Override
	public Computer find(final int id) {
		LOGGER.info("Find computer " + id);
		return (Computer) sessionFactory.getCurrentSession().get(
				Computer.class, id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#find(java.lang.String,
	 * com.excilys.cdb.util.sort.SortCriteria)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> find(final String name,
			final SortCriteria sortCriteria) {
		LOGGER.info("Find computers by name : " + name);

		final StringBuffer correctName = new StringBuffer("%").append(name)
				.append("%");

		final Criterion computerName = Restrictions.like("computer.name",
				correctName.toString());
		final Criterion companyName = Restrictions.like("company.name",
				correctName.toString());
		final LogicalExpression orExp = Restrictions.or(computerName,
				companyName);

		final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
				.add(orExp);

		if (sortCriteria != null) {
			final Order order = getOrder(sortCriteria);
			criteria.addOrder(order);
		}

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#findByCompanyId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findByCompanyId(final int companyId) {
		LOGGER.info("Find computers by company id : " + companyId);
		return sessionFactory.getCurrentSession()
				.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.like("company.id", companyId)).list();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.persistence.ComputerDao#create(com.excilys.cdb.model.
	 * Computer)
	 */
	@Override
	public Computer create(final Computer computer) {
		final int id = (int) sessionFactory.getCurrentSession().save(computer);
		computer.setId(id);
		LOGGER.info("Create computer " + computer);
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
	public Computer update(final Computer computer) {
		LOGGER.info("Update computer " + computer);
		sessionFactory.getCurrentSession().merge(computer);
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
	public void delete(final int id) {
		LOGGER.info("Delete computer whit id + " + id);
		final Session session = sessionFactory.getCurrentSession();
		final Computer computer = (Computer) session.get(Computer.class, id);
		if (computer == null) {
			return;
		}
		session.delete(computer);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#deleteByCompanyId(int)
	 */
	@Override
	public void deleteByCompanyId(final int companyId) {
		LOGGER.info("Delete computer whit company id + " + companyId);
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		final List<Computer> computers = session.createCriteria(Computer.class)
		.add(Restrictions.eq("company.id", companyId)).list();
		session.delete(computers);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#count()
	 */
	@Override
	public long count() {
		final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Computer.class);
		return (long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAll(final SortCriteria sortCriteria) {
		LOGGER.info("final all");
		final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		if (sortCriteria != null) {
			LOGGER.info("final all with criteria " + sortCriteria.toString());
			final Order order = getOrder(sortCriteria);
			criteria.addOrder(order);
		}
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAll(final int start, final int offset,
			final SortCriteria sortCriteria) {
		LOGGER.info("final all  start : {}, offset : {} ", start, offset);

		final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		if (sortCriteria != null) {
			final Order order = getOrder(sortCriteria);
			criteria.addOrder(order);
		}
		return criteria.setFirstResult(start).setMaxResults(offset).list();
	}

	private Order getOrder(SortCriteria sortCriteria) {
		return (sortCriteria.getSortDirection() == SortDirection.ASC) ? Order
				.asc(sortCriteria.getColumn()) : Order.desc(sortCriteria
						.getColumn());
	}

}
