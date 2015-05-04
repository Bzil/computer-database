/**
 * 
 * @author Basile
 */
package com.excilys.cdb.api.dto;

import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlRootElement;

import com.excilys.cdb.model.Computer;

/**
 * The Class ComputerJson.
 */
@XmlRootElement
public class ComputerJson {

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/** The introduced. */
	private String introduced;

	/** The discontinued. */
	private String discontinued;

	/** The company. */
	private CompanyJson company;

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
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public String getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced
	 *            the new introduced
	 */
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public String getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued
	 *            the new discontinued
	 */
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public CompanyJson getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company
	 *            the new company
	 */
	public void setCompany(CompanyJson company) {
		this.company = company;
	}

	/**
	 * To.
	 *
	 * @param computer the computer
	 * @return the computer json
	 */
	public static ComputerJson to(Computer computer) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		final ComputerJson computerView = new ComputerJson();
		computerView.id = computer.getId();
		computerView.name = computer.getName();
		if (computer.getIntroduced() != null) {
			computerView.introduced = computer.getIntroduced().format(formatter);
		} else {
			computerView.introduced = "";
		}
		if (computer.getDiscontinued() != null) {
			computerView.discontinued = computer.getDiscontinued().format(formatter);
		} else {
			computerView.discontinued = "";
		}
		if (computer.getCompany() != null && computer.getCompany().getName() != null
				&& !computer.getCompany().getName().trim().isEmpty()) {
			computerView.company = CompanyJson.to(computer.getCompany());
		} else {
			computerView.company = null;
		}
		return computerView;
	}

	/**
	 * From.
	 *
	 * @param computerView the computer view
	 * @return the computer
	 */
	public static Computer from(ComputerJson computerView) {
		final Computer.Builder builder = Computer.builder(computerView.name.trim()).id(computerView.id);
		if (computerView.introduced != null && !computerView.introduced.trim().isEmpty()) {
			builder.introduced(computerView.introduced);
		}
		if (computerView.discontinued != null && !computerView.discontinued.trim().isEmpty()) {
			builder.introduced(computerView.discontinued);
		}
		if (computerView.company != null) {
			builder.company(CompanyJson.from(computerView.company));
		}
		return builder.build();
	}
}
