package com.excilys.cdb.api.dto;

import java.time.format.DateTimeFormatter;

import com.excilys.cdb.model.Computer;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ComputerJson {

	private int id;

	private String name;

	private String introduced;

	private String discontinued;

	private CompanyJson company;

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

	public CompanyJson getCompany() {
		return company;
	}

	public void setCompany(CompanyJson company) {
		this.company = company;
	}

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
