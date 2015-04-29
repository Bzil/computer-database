package com.excilys.cdb.service;

import com.excilys.cdb.dto.DTO;
import com.excilys.cdb.sort.SortCriteria;

import java.util.List;

/**
 * The Interface Service.
 *
 * @param <T> the generic type
 * @param <D> the generic type
 */
public interface ServiceCommons<T, D extends DTO<T>> {

    /**
     * Find.
     *
     * @param id the id
     * @return the t
     */
    D find(int id);

    /**
     * Find all.
     *
     * @return the list
     */
    List<D> findAll(SortCriteria criteria);

    /**
     * Find all.
     *
     * @param start  the start
     * @param offset the offset
     * @return the list
     */
    List<D> findAll(int start, int offset, SortCriteria criteria);

    /**
     * Adds the.
     *
     * @param t the t
     * @return the t
     */
    default D add(final T t) {
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
    default D update(final T t) {
        throw new UnsupportedOperationException();
    }

    /**
     * Count.
     *
     * @return c
     */
    default long count() {
        throw new UnsupportedOperationException();
    }

}
