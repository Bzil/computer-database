package com.excilys.cdb.service;

import java.util.List;

public interface Service<T> {

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	public T find(int id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<T> findAll();

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<T> findAll(int start, int offset);

	/**
	 * Adds the.
	 *
	 * @param t
	 *            the t
	 * @return the t
	 */
	public T add(T t);

	/**
	 * Delete.
	 *
	 * @param t
	 *            the t
	 */
	public void delete(T t);

	/**
	 * Update.
	 *
	 * @param t
	 *            the t
	 * @return the t
	 */
	public T update(T t);

	/**
	 * Count.
	 *
	 * @return c
	 */
	public int count();

}
