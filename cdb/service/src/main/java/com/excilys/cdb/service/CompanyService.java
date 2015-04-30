package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.sort.SortCriteria;

/**
 * The Interface CompanyService.
 */
public interface CompanyService extends ServiceCommons<Company> {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	@Override
	public Company find(int id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Company> findAll(SortCriteria criteria);

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
	public List<Company> findAll(int start, int offset, SortCriteria criteria);

	/**
	 * Delete.
	 *
	 * @param company
	 *            the company
	 */
	@Override
	public void delete(Company company);

}
