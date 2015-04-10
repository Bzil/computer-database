package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.sort.SortCriteria;

/**
 * The Interface CompanyService.
 */
public interface CompanyService extends ServiceCommons<Company, CompanyDTO> {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	public CompanyDTO find(int id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<CompanyDTO> findAll(SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<CompanyDTO> findAll(int start, int offset, SortCriteria criteria);

	/**
	 * Adds the.
	 *
	 * @param company
	 *            the company
	 * @return the company
	 */
	public CompanyDTO add(Company company);

	/**
	 * Delete.
	 *
	 * @param company
	 *            the company
	 */
	public void delete(Company company);

	/**
	 * Update.
	 *
	 * @param company
	 *            the company
	 * @return the company
	 */
	public CompanyDTO update(Company company);

	/**
	 * Count.
	 *
	 * @return c
	 */
	public int count();
}
