package com.excilys.cdb.persistence.impl;

import com.excilys.cdb.model.User;
import com.excilys.cdb.persistence.UserDao;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public User findByName(String username) {
        LOGGER.info("Find user by name : {} ", username);

        final StringBuilder correctName = new StringBuilder("%").append(username)
                .append("%");

        final Criterion criterion = Restrictions.like("u.username",
                correctName.toString());

        final List<User> users = sessionFactory.getCurrentSession()
                .createCriteria(User.class, "u").add(criterion).list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

}
