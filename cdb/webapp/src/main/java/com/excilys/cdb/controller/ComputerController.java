package com.excilys.cdb.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.excilys.cdb.controller.dto.ComputerView;

/**
 * The Interface ComputerController.
 */
public interface ComputerController {

	/**
	 * Gets the computer list.
	 *
	 * @param id
	 *            the id
	 * @param size
	 *            the size
	 * @param search
	 *            the search
	 * @param column
	 *            the column
	 * @param dir
	 *            the dir
	 * @param selection
	 *            the selection
	 * @param model
	 *            the model
	 * @return the computer list
	 */
	public String getComputerList(final Integer id, final Integer size, final String search, final String column,
			final String dir, final String selection, final Model model);

	/**
	 * Load.
	 *
	 * @param id
	 *            the id
	 * @param computerDto
	 *            the computer dto
	 * @param model
	 *            the model
	 * @return the string
	 */
	public String load(final Integer id, ComputerView computerDto, Model model);

	/**
	 * Edits the.
	 *
	 * @param computerDto
	 *            the computer dto
	 * @param result
	 *            the result
	 * @param model
	 *            the model
	 * @return the string
	 */
	public String edit(ComputerView computerDto, BindingResult result, Model model);

	/**
	 * Delete.
	 *
	 * @param selection
	 *            the selection
	 * @return the string
	 */
	public String delete(String selection);
}
