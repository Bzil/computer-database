/**
 * 
 * @author Basile
 */
package com.excilys.cdb.persistence;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.sort.SortCriteria;

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
	Computer find(int id);

	/**
	 * Find.
	 *
	 * @param name the name
	 * @param criteria the criteria
	 * @return the list
	 */
	List<Computer> find(String name, SortCriteria criteria);

	/**
	 * Find.
	 *
	 * @param name the name
	 * @param start the start
	 * @param offset the offset
	 * @param criteria the criteria
	 * @return the list
	 */
	List<Computer> find(String name, int start, int offset, SortCriteria criteria);

	/**
	 * Find by company id.
	 *
	 * @param companyId the company id
	 * @return the list
	 */
	List<Computer> findByCompanyId(int companyId);

	/**
	 * Find all.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	List<Computer> findAll(SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @param start the start
	 * @param offset the offset
	 * @param criteria the criteria
	 * @return the list
	 */
	List<Computer> findAll(int start, int offset, SortCriteria criteria);

	/**
	 * Creates the.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	Computer create(Computer computer);

	/**
	 * Update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	Computer update(Computer computer);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(int id);

	/**
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 */
	void deleteByCompanyId(int companyId);

	/**
	 * Count.
	 *
	 * @return the long
	 */
	long count();
}
