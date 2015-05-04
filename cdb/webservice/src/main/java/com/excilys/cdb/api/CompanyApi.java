/**
 *
 * @author Basile
 */
package com.excilys.cdb.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.excilys.cdb.api.dto.CompanyJson;

/**
 * The Interface CompanyApi.
 */
public interface CompanyApi {

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	ResponseEntity<CompanyJson> getById(Integer id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	ResponseEntity<List<CompanyJson>> getAll();

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	ResponseEntity<CompanyJson> delete(Integer id);
}
