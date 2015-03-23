package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerService.
 */
public interface ComputerService extends Service<Computer>{

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	public Computer find(int id);
	
	/**
	 * Find.
	 *
	 * @param name the name
	 * @return the computer
	 */
	public Computer find(String name);	

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
	
	/**
	 * Count.
	 *
	 * @return c
	 */
	public int count();
}
