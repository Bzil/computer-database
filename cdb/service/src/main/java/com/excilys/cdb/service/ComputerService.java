/**
 * 
 * @author Basile
 */
package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.sort.SortCriteria;

/**
 * The Interface ComputerService.
 */
public interface ComputerService extends ServiceCommons<Computer> {

	/* (non-Javadoc)
	 * @see com.excilys.cdb.service.ServiceCommons#find(int)
	 */
	@Override
	public Computer find(int id);

	/**
	 * Find.
	 *
	 * @param name the name
	 * @param criteria the criteria
	 * @return the list
	 */
	public List<Computer> find(String name, SortCriteria criteria);

	/**
	 * Find.
	 *
	 * @param name the name
	 * @param start the start
	 * @param offset the offset
	 * @param criteria the criteria
	 * @return the list
	 */
	public List<Computer> find(String name, int start, int offset, SortCriteria criteria);

	/* (non-Javadoc)
	 * @see com.excilys.cdb.service.ServiceCommons#findAll(com.excilys.cdb.sort.SortCriteria)
	 */
	@Override
	public List<Computer> findAll(SortCriteria criteria);

	/* (non-Javadoc)
	 * @see com.excilys.cdb.service.ServiceCommons#findAll(int, int, com.excilys.cdb.sort.SortCriteria)
	 */
	@Override
	public List<Computer> findAll(int start, int offset, SortCriteria criteria);

	/* (non-Javadoc)
	 * @see com.excilys.cdb.service.ServiceCommons#add(java.lang.Object)
	 */
	@Override
	public Computer add(Computer computer);

	/* (non-Javadoc)
	 * @see com.excilys.cdb.service.ServiceCommons#delete(java.lang.Object)
	 */
	@Override
	public void delete(Computer computer);

	/**
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 */
	public void deleteByCompanyId(int companyId);

	/* (non-Javadoc)
	 * @see com.excilys.cdb.service.ServiceCommons#update(java.lang.Object)
	 */
	@Override
	public Computer update(Computer computer);

	/**
	 * Save or update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer saveOrUpdate(Computer computer);

	/* (non-Javadoc)
	 * @see com.excilys.cdb.service.ServiceCommons#count()
	 */
	@Override
	public long count();
}
