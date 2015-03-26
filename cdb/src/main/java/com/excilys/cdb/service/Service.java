package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.util.dto.DTO;

/**
 * The Interface Service.
 *
 * @param <T> the generic type
 * @param <D> the generic type
 */
public interface Service<T, D extends DTO<T>> {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	public D find(int id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<D> findAll();

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<D> findAll(int start, int offset);

	/**
	 * Adds the.
	 *
	 * @param t
	 *            the t
	 * @return the t
	 */
	public D add(T t);

	/**
	 * Delete.
	 *
	 * @param t
	 *            the t
	 */
	public void delete(T t);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(int id);

	/**
	 * Update.
	 *
	 * @param t
	 *            the t
	 * @return the t
	 */
	public D update(T t);

	/**
	 * Count.
	 *
	 * @return c
	 */
	public int count();

}
