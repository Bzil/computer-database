/**
 *
 * @author Basile
 */
package com.excilys.cdb.persistence;

import java.util.List;

import com.excilys.cdb.model.Company;

/**
 * The Interface CompanyDao.
 */
public interface CompanyDao {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	Company find(int id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Company> findAll();

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	List<Company> findAll(int start, int offset);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	void delete(int id);

}
