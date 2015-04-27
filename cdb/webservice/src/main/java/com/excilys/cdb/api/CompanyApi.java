package com.excilys.cdb.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.excilys.cdb.dto.CompanyDTO;

public interface CompanyApi {

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public ResponseEntity<CompanyDTO> getById(Integer id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public ResponseEntity<List<CompanyDTO>> getAll();

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response
	 */
	public ResponseEntity<CompanyDTO> delete(Integer id);
}
