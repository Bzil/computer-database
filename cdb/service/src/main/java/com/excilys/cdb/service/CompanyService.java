/**
 *
 * @author Basile
 */
package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.sort.SortCriteria;

/**
 * The Interface CompanyService.
 */
public interface CompanyService extends ServiceCommons<Company> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ServiceCommons#find(int)
	 */
	@Override
	public Company find(int id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ServiceCommons#findAll(com.excilys.cdb.sort.
	 * SortCriteria)
	 */
	@Override
	public List<Company> findAll(SortCriteria criteria);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ServiceCommons#findAll(int, int,
	 * com.excilys.cdb.sort.SortCriteria)
	 */
	@Override
	public List<Company> findAll(int start, int offset, SortCriteria criteria);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ServiceCommons#delete(java.lang.Object)
	 */
	@Override
	public void delete(Company company);

}
