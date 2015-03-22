package com.excilys.cdb.util.dto;

import java.time.LocalDateTime;

import com.excilys.cdb.model.Computer;

public class ComputerDTO implements DTO<Computer> {

	public int id;
	public String name;
	public LocalDateTime introduced;
	public LocalDateTime discontinued;
	public String companyName;

	public static ComputerDTO toDTO(Computer computer) {
		ComputerDTO dto = new ComputerDTO();
		dto.id = computer.getId();
		dto.name = computer.getName();
		dto.introduced = computer.getIntroduced();
		dto.discontinued = computer.getDiscontinued();
		dto.companyName = (computer.getCompany() != null ) ? computer.getCompany().getName() : "" ;
		return dto;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		return new StringBuilder("Computer [id=").append(id).append(" name : ")
				.append(name).append(" introduced : ").append(introduced)
				.append(" discontinued : ").append(discontinued)
				.append(" company name : ").append(companyName).append(" ]")
				.toString();
	}

}
