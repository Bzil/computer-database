/**
 * 
 * @author Basile
 */
package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.sort.SortCriteria;

/**
 * The Interface ServiceCommons.
 *
 * @param <T> the generic type
 */
public interface ServiceCommons<T> {

	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the t
	 */
	T find(int id);

	/**
	 * Find all.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	List<T> findAll(SortCriteria criteria);

	/**
	 * Find all.
	 *
	 * @param start the start
	 * @param offset the offset
	 * @param criteria the criteria
	 * @return the list
	 */
	List<T> findAll(int start, int offset, SortCriteria criteria);

	/**
	 * Adds the.
	 *
	 * @param t the t
	 * @return the t
	 */
	default T add(final T t) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Delete.
	 *
	 * @param t the t
	 */
	void delete(T t);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(int id);

	/**
	 * Update.
	 *
	 * @param t the t
	 * @return the t
	 */
	default T update(final T t) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Count.
	 *
	 * @return the long
	 */
	default long count() {
		throw new UnsupportedOperationException();
	}

}
