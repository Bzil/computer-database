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
	public Company find(int id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Company> findAll();

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<Company> findAll(int start, int offset);

	/**
	 * Delete.
	 *
	 * @param company
	 *            the company
	 */
	public void delete(int id);

}