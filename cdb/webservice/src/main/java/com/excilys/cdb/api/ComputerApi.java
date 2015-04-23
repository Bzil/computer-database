package com.excilys.cdb.api;

import javax.ws.rs.core.Response;

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
	public Response getById(Integer id);

	/**
	 * Gets the by name.
	 *
	 * @param name
	 *            the name
	 * @return the by name
	 */
	public Response getByName(String name);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public Response getAll();

	/**
	 * Gets the all by criteria.
	 *
	 * @param name
	 *            the name
	 * @param order
	 *            the order
	 * @return the all by criteria
	 */
	public Response getAllByCriteria(String name, String order);

	/**
	 * Gets the page.
	 *
	 * @param name
	 *            the name
	 * @param order
	 *            the order
	 * @param start
	 *            the start
	 * @param offset
	 *            the offset
	 * @return the page
	 */
	public Response getPage(String name, String order, Integer start,
			Integer offset);
}
