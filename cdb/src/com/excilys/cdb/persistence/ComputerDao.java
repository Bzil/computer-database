package com.excilys.cdb.persistence;

import java.util.List;

import com.excilys.cdb.model.Computer;

/**
 * The Interface ComputerDao.
 */
public interface ComputerDao {
	
	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the computer
	 */
	public Computer find(int id);
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Computer> findAll();

	/**
	 * Creates the.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer create(Computer computer);

	/**
	 * Update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer update(Computer computer);

	/**
	 * Delete.
	 *
	 * @param computer the computer
	 */
	public void delete(Computer computer);
	
	/**
	 * Count.
	 *
	 * @return the int
	 */
	public int count();
}
