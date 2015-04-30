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
	 * Creates the Computer with required parameter name, id = -1, companyId
	 * Optional parameter are introduced, discontinued with pattern
	 * <u>dd-mm-yyyy</u> and companyName. For example <i>{"id":"-1",
	 * "name":"xyz", "companyId":"-1"}</i> will return
	 * <i>{"id":XX,"name":"xyz","introduced"
	 * :"","discontinued":"","companyId":-1,"companyName":""}</i> where XX is
	 * the new id of the computer.
	 *
	 * @param dto
	 *            the dto
	 * @return the response
	 */
	ResponseEntity<ComputerJson> create(ComputerJson dto);

	/**
	 * Update the Computer with required parameter name, id = XX, companyId
	 * Optional parameter are introduced, discontinued with pattern
	 * <u>dd-mm-yyyy</u> and companyName. For example <i>{"id":"XX",
	 * "name":"xyz", "companyId":"-1"}</i> will return
	 * <i>{"id":XX,"name":"xyz","introduced"
	 * :"","discontinued":"","companyId":-1,"companyName":""}</i> where XX is
	 * the id of the computer.
	 *
	 * @param dto
	 *            the dto
	 * @return the response
	 */
	ResponseEntity<ComputerJson> update(ComputerJson dto);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response
	 */
	ResponseEntity<ComputerJson> delete(Integer id);

}
