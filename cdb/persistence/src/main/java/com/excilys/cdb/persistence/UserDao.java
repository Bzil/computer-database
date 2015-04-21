package com.excilys.cdb.persistence;

import com.excilys.cdb.model.User;

/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Find user by name.
	 *
	 * @param username
	 *            the username
	 * @return the user
	 */
	public User findByName(String username);

}
