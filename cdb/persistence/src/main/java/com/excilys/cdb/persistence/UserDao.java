package com.excilys.cdb.persistence;

import com.excilys.cdb.persistence.dto.UserJPA;

/**
 * The Interface UserDao.
 */
public interface UserDao {

    /**
     * Find user by name.
     *
     * @param username the username
     * @return the user
     */
    UserJPA findByName(String username);

}
