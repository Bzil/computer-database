package com.excilys.cdb.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.excilys.cdb.dto.ComputerDTO;

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
	public ResponseEntity<ComputerDTO> getById(Integer id);

	/**
	 * Gets the by name.
	 *
	 * @param name
	 *            the name
	 * @return the by name
	 */
	public ResponseEntity<List<ComputerDTO>> getByName(String name);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public ResponseEntity<List<ComputerDTO>> getAll();

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
	public ResponseEntity<ComputerDTO> create(ComputerDTO dto);

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
	public ResponseEntity<ComputerDTO> update(ComputerDTO dto);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the response
	 */
	public ResponseEntity<ComputerDTO> delete(Integer id);

}
