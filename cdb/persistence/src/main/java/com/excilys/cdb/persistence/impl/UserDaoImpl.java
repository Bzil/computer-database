/**
 * 
 * @author Basile
 */
package com.excilys.cdb.persistence.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.persistence.UserDao;
import com.excilys.cdb.persistence.dto.UserJPA;

/**
 * The Class UserDaoImpl.
 */
@Repository
public class UserDaoImpl implements UserDao {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.UserDao#findByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserJPA findByName(String username) {
		LOGGER.info("Find user by name : {} ", username);

		final StringBuilder correctName = new StringBuilder("%").append(username).append("%");

		final Criterion criterion = Restrictions.like("u.username", correctName.toString());

		final List<UserJPA> userJPAs = sessionFactory.getCurrentSession().createCriteria(UserJPA.class, "u")
				.add(criterion).list();

		if (userJPAs.size() > 0) {
			return userJPAs.get(0);
		} else {
			return null;
		}
	}

}
