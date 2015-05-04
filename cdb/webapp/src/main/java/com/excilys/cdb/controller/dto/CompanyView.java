/**
 *
 * @author Basile
 */
package com.excilys.cdb.controller.dto;

import com.excilys.cdb.model.Company;

/**
 * The Class CompanyView.
 */
public class CompanyView {

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new company view.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public CompanyView(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * To.
	 *
	 * @param company
	 *            the company
	 * @return the company view
	 */
	public static CompanyView to(Company company) {
		return new CompanyView(company.getId(), company.getName());
	}

	/**
	 * From.
	 *
	 * @param company
	 *            the company
	 * @return the company
	 */
	public static Company from(CompanyView company) {
		return Company.builder().id(company.getId()).name(company.getName()).build();
	}

}
