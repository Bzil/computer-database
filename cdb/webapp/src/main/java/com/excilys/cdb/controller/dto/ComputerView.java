/**
 * 
 * @author Basile
 */
package com.excilys.cdb.controller.dto;

import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.cdb.annotation.Date;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * The Class ComputerView.
 */
public class ComputerView {

	/** The id. */
	private int id;

	/** The name. */
	@NotEmpty(message = "{validation.empty}")
	@NotBlank(message = "{validation.blank.char}")
	@Size(min = 3, max = 100)
	@Pattern(message = "{validation.start.blank}", regexp = "^(?![ ]+).*$")
	private String name;

	/** The introduced. */
	@Date
	private String introduced;

	/** The discontinued. */
	@Date
	private String discontinued;

	/** The company name. */
	private String companyName;

	/** The company id. */
	private int companyId;

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
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName
	 *            the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId
	 *            the new company id
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * To.
	 *
	 * @param computer the computer
	 * @return the computer view
	 */
	public static ComputerView to(Computer computer) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		final ComputerView computerView = new ComputerView();
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
			computerView.companyName = computer.getCompany().getName().trim();
			computerView.companyId = computer.getCompany().getId();
		} else {
			computerView.companyName = "";
		}
		return computerView;
	}

	/**
	 * From.
	 *
	 * @param computerView the computer view
	 * @return the computer
	 */
	public static Computer from(ComputerView computerView) {
		final Computer.Builder builder = Computer.builder(computerView.name.trim()).id(computerView.id);
		if (computerView.introduced != null && !computerView.introduced.trim().isEmpty()) {
			builder.introduced(computerView.introduced);
		}
		if (computerView.discontinued != null && !computerView.discontinued.trim().isEmpty()) {
			builder.introduced(computerView.discontinued);
		}
		if (computerView.companyName != null && !computerView.companyName.trim().isEmpty()) {
			builder.company(new Company(computerView.companyId, computerView.companyName));
		}
		return builder.build();
	}

}
