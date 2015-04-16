package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.util.dto.DTO;
import com.excilys.cdb.util.sort.SortCriteria;

/**
 * The Interface Service.
 *
 * @param <T>
 *            the generic type
 * @param <D>
 *            the generic type
 */
public interface ServiceCommons<T, D extends DTO<T>> {

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
	public List<D> findAll(SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<D> findAll(int start, int offset, SortCriteria criteria);

	/**
	 * Adds the.
	 *
	 * @param t
	 *            the t
	 * @return the t
	 */
	public default D add(final T t) {
		throw new UnsupportedOperationException();
	};

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
	 * @param id
	 *            the id
	 */
	public void delete(int id);

	/**
	 * Update.
	 *
	 * @param t
	 *            the t
	 * @return the t
	 */
	public default D update(final T t) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Count.
	 *
	 * @return c
	 */
	public default long count() {
		throw new UnsupportedOperationException();
	}

}
