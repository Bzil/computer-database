/*
 * 
 */
package com.excilys.cdb.persistence;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.sort.SortCriteria;

// TODO: Auto-generated Javadoc
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
	 * Find.
	 *
	 * @param name the name
	 * @param criteria the criteria
	 * @return the computer
	 */
	public List<Computer> find(String name, SortCriteria criteria);
	
	/**
	 * Find.
	 *
	 * @param companyId the company id
	 * @return the computer
	 */
	public List<Computer> findByCompanyId(int companyId);

	/**
	 * Find all.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	public List<Computer> findAll(SortCriteria criteria);
	
	/**
	 * Find all.
	 *
	 * @param start the start
	 * @param offset the offset
	 * @param criteria the criteria
	 * @return the list
	 */
	public List<Computer> findAll(int start, int offset, SortCriteria criteria);
	/**
	 * Creates the.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	public Computer create(Computer computer);

	/**
	 * Update.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	public Computer update(Computer computer);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(int id);	
	
	/**
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 */
	public void deleteByCompanyId(int companyId);

	/**
	 * Count.
	 *
	 * @return the int
	 */
	public int count();
}
