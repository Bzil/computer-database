package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;

/**
 * The Interface ComputerService.
 */
public interface ComputerService {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
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
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<Computer> findAll(int start, int offset);

	/**
	 * Adds the.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	public Computer add(Computer computer);

	/**
	 * Delete.
	 *
	 * @param computer
	 *            the computer
	 */
	public void delete(Computer computer);

	/**
	 * Update.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	public Computer update(Computer computer);
}
