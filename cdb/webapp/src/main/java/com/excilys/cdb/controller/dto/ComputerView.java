package com.excilys.cdb.controller.dto;

import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.cdb.annotation.Date;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerView {

	private int id;

	@NotEmpty(message = "{validation.empty}")
	@NotBlank(message = "{validation.blank.char}")
	@Size(min = 3, max = 100)
	@Pattern(message = "{validation.start.blank}", regexp = "^(?![ ]+).*$")
	private String name;

	@Date
	private String introduced;

	@Date
	private String discontinued;

	private String companyName;

	private int companyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

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
