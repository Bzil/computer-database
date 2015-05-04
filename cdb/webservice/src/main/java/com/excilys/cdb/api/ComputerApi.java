/**
 *
 * @author Basile
 */
package com.excilys.cdb.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.excilys.cdb.api.dto.ComputerJson;

/**
 * The Interface ComputerApi.
 */
public interface ComputerApi {

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	ResponseEntity<ComputerJson> getById(Integer id);

	/**
	 * Gets the by name.
	 *
	 * @param name
	 *            the name
	 * @return the by name
	 */
	ResponseEntity<List<ComputerJson>> getByName(String name);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	ResponseEntity<List<ComputerJson>> getAll();

	/**
	 * Creates the.
	 *
	 * @param dto
	 *            the dto
	 * @return the response entity
	 */
	ResponseEntity<ComputerJson> create(ComputerJson dto);

	/**
	 * Update.
	 *
	 * @param dto
	 *            the dto
	 * @return the response entity
	 */
	ResponseEntity<ComputerJson> update(ComputerJson dto);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	ResponseEntity<ComputerJson> delete(Integer id);

}
