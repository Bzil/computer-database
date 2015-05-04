/**
 *
 * @author Basile
 */
package com.excilys.cdb.api.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.excilys.cdb.model.Company;

/**
 * The Class CompanyJson.
 */
@XmlRootElement
public class CompanyJson {

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new company json.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public CompanyJson(int id, String name) {
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
	 * @return the company json
	 */
	public static CompanyJson to(Company company) {
		return new CompanyJson(company.getId(), company.getName());
	}

	/**
	 * From.
	 *
	 * @param company
	 *            the company
	 * @return the company
	 */
	public static Company from(CompanyJson company) {
		return Company.builder().id(company.id).name(company.name).build();
	}

}
