package com.excilys.cdb.api;

import javax.ws.rs.core.Response;

public interface CompanyApi {

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public Response getById(Integer id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public Response getAll();

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response
	 */
	public Response delete(Integer id);
}
