package com.excilys.cdb.persistence.impl;

import java.util.List;
import java.util.stream.Collectors;

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
import com.excilys.cdb.persistence.dto.ComputerJPA;
import com.excilys.cdb.sort.SortCriteria;
import com.excilys.cdb.sort.SortDirection;

/**
 * The Class ComputerDaoImpl.
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new computer dao impl.
	 */
	private ComputerDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#find(int)
	 */
	@Override
	public Computer find(final int id) {
		LOGGER.info("Find computer {}", id);
		ComputerJPA c = (ComputerJPA) sessionFactory.getCurrentSession().get(ComputerJPA.class, id);
		LOGGER.info("Find computer JPA{}", c);
		return ComputerJPA.from(c);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#find(java.lang.String,
	 * com.excilys.cdb.util.sort.SortCriteria)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> find(final String name, final SortCriteria sortCriteria) {
		LOGGER.info("Find computers by name : {}", name);
		final StringBuilder correctName = new StringBuilder("%").append(name).append("%");
		final Criterion computerName = Restrictions.like("computer.name", correctName.toString());
		final Criterion companyName = Restrictions.like("company.name", correctName.toString());
		final LogicalExpression orExp = Restrictions.or(computerName, companyName);

		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ComputerJPA.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN).add(orExp);

		if (sortCriteria != null) {
			final Order order = getOrder(sortCriteria);
			criteria.addOrder(order);
		}

		final List<ComputerJPA> list = criteria.list();
		return list.stream().map(ComputerJPA::from).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> find(final String name, final int start, final int offset, final SortCriteria sortCriteria) {
		LOGGER.info("Find computers by name : {}", name);
		final StringBuilder correctName = new StringBuilder("%").append(name).append("%");
		final Criterion computerName = Restrictions.like("computer.name", correctName.toString());
		final Criterion companyName = Restrictions.like("company.name", correctName.toString());
		final LogicalExpression orExp = Restrictions.or(computerName, companyName);

		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ComputerJPA.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN).add(orExp);

		if (sortCriteria != null) {
			final Order order = getOrder(sortCriteria);
			criteria.addOrder(order);
		}

		final List<ComputerJPA> list = criteria.setFirstResult(start).setMaxResults(offset).list();
		return list.stream().map(ComputerJPA::from).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#findByCompanyId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findByCompanyId(final int companyId) {
		LOGGER.info("Find computers by company id : {}", companyId);
		final List<ComputerJPA> list = sessionFactory.getCurrentSession().createCriteria(ComputerJPA.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.like("company.id", companyId)).list();
		return list.stream().map(ComputerJPA::from).collect(Collectors.toList());
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
		final int id = (int) sessionFactory.getCurrentSession().save(ComputerJPA.to(computer));
		computer.setId(id);
		LOGGER.info("Create computer {}", computer);
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
		LOGGER.info("Update computer {}", computer);
		sessionFactory.getCurrentSession().merge(ComputerJPA.to(computer));
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
		LOGGER.info("Delete computer with id {} ", id);
		final Session session = sessionFactory.getCurrentSession();
		final ComputerJPA computer = (ComputerJPA) session.get(ComputerJPA.class, id);

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
	@SuppressWarnings("unchecked")
	@Override
	public void deleteByCompanyId(final int companyId) {
		LOGGER.info("Delete computer whit company id {} ", companyId);
		final Session session = sessionFactory.getCurrentSession();
		final List<ComputerJPA> computers = session.createCriteria(ComputerJPA.class)
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
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ComputerJPA.class);
		final long count = (long) criteria.setProjection(Projections.rowCount()).list().get(0);
		LOGGER.info("Count {}", count);
		return count;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAll(final SortCriteria sortCriteria) {
		LOGGER.info("find all");
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ComputerJPA.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		if (sortCriteria != null) {
			LOGGER.info("final all with criteria {}", sortCriteria.toString());
			final Order order = getOrder(sortCriteria);
			criteria.addOrder(order);
		}
		final List<ComputerJPA> list = criteria.list();
		return list.stream().map(ComputerJPA::from).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.persistence.ComputerDao#findAll(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAll(final int start, final int offset, final SortCriteria sortCriteria) {
		LOGGER.info("find all  start : {}, offset : {} ", start, offset);

		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ComputerJPA.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		if (sortCriteria != null) {
			final Order order = getOrder(sortCriteria);
			criteria.addOrder(order);
		}

		final List<ComputerJPA> list = criteria.setFirstResult(start).setMaxResults(offset).list();
		return list.stream().map(ComputerJPA::from).collect(Collectors.toList());
	}

	private Order getOrder(SortCriteria sortCriteria) {
		return (sortCriteria.getSortDirection() == SortDirection.ASC) ? Order.asc(sortCriteria.getColumn()) : Order
				.desc(sortCriteria.getColumn());
	}

}
