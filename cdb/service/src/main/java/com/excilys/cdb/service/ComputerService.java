package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.sort.SortCriteria;

/**
 * The Interface ComputerService.
 */
public interface ComputerService extends ServiceCommons<Computer> {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	@Override
	public Computer find(int id);

	/**
	 * Find.
	 *
	 * @param name
	 *            the name
	 * @return the computer dto
	 */
	public List<Computer> find(String name, SortCriteria criteria);

	/**
	 * Find.
	 *
	 * @param name
	 *            the name
	 * @return the computer dto
	 */
	public List<Computer> find(String name, int start, int offset, SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Computer> findAll(SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	@Override
	public List<Computer> findAll(int start, int offset, SortCriteria criteria);

	/**
	 * Adds the.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	@Override
	public Computer add(Computer computer);

	/**
	 * Delete.
	 *
	 * @param computer
	 *            the computer
	 */
	@Override
	public void delete(Computer computer);

	/**
	 * Delete by company id.
	 *
	 * @param companyId
	 *            the company id
	 */
	public void deleteByCompanyId(int companyId);

	/**
	 * Update.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	@Override
	public Computer update(Computer computer);

	/**
	 * Save or update.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer dto
	 */
	public Computer saveOrUpdate(Computer computer);

	/**
	 * Count.
	 *
	 * @return c
	 */
	@Override
	public long count();
}
