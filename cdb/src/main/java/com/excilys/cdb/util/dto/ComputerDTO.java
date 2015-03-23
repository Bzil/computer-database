package com.excilys.cdb.util.dto;

import java.time.format.DateTimeFormatter;

import com.excilys.cdb.model.Computer;

public class ComputerDTO implements DTO<Computer> {

	public String name;
	public String introduced;
	public String discontinued;
	public String companyName;

	public static ComputerDTO toDTO(Computer computer) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ComputerDTO dto = new ComputerDTO();
		dto.name = computer.getName();
		dto.introduced =   computer.getIntroduced() != null   ? computer.getIntroduced().format(formatter) : "";
		dto.discontinued = computer.getDiscontinued() != null ? computer.getDiscontinued().format(formatter) : "";
		dto.companyName = (computer.getCompany() != null ) ? computer.getCompany().getName() : "" ;
		return dto;
	}

	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		return new StringBuilder("Computer [name : ")
				.append(name).append(" introduced : ").append(introduced)
				.append(" discontinued : ").append(discontinued)
				.append(" company name : ").append(companyName).append(" ]")
				.toString();
	}

}
