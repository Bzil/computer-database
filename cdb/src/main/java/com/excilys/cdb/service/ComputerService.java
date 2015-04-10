/*
 * 
 */
package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.dto.ComputerDTO;
import com.excilys.cdb.util.sort.SortCriteria;

/**
 * The Interface ComputerService.
 */
public interface ComputerService extends ServiceCommons<Computer, ComputerDTO> {

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
	public List<ComputerDTO> find(String name, SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ComputerDTO> findAll(SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<ComputerDTO> findAll(int start, int offset, SortCriteria criteria);

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
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 */
	public void deleteByCompanyId(int companyId);

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
