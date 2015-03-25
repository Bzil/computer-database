package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.dto.ComputerDTO;

/**
 * The Interface ComputerService.
 */
public interface ComputerService extends Service<Computer, ComputerDTO> {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	public ComputerDTO find(int id);

	/**
	 * Find.
	 *
	 * @param name
	 *            the name
	 * @return the computer dto
	 */
	public ComputerDTO find(String name);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ComputerDTO> findAll();

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<ComputerDTO> findAll(int start, int offset);

	/**
	 * Adds the.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	public ComputerDTO add(Computer computer);

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
	public ComputerDTO update(Computer computer);

	/**
	 * Count.
	 *
	 * @return c
	 */
	public int count();
}
