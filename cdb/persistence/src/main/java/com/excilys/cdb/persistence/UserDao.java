/**
 * 
 * @author Basile
 */
package com.excilys.cdb.persistence;

import com.excilys.cdb.persistence.dto.UserJPA;

/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Find by name.
	 *
	 * @param username the username
	 * @return the user jpa
	 */
	UserJPA findByName(String username);

}
