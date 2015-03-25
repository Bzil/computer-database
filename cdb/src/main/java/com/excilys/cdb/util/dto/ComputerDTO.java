package com.excilys.cdb.util.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerDTO implements DTO<Computer> {

	public int id;
	public String name;
	public String introduced;
	public String discontinued;

	public int companyId;
	public String companyName;

	public static ComputerDTO toDTO(Computer computer) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ComputerDTO dto = new ComputerDTO();
		dto.id = computer.getId();
		dto.name = computer.getName();
		dto.introduced = computer.getIntroduced() != null ? computer
				.getIntroduced().format(formatter) : "";
		dto.discontinued = computer.getDiscontinued() != null ? computer
				.getDiscontinued().format(formatter) : "";
		dto.companyName = (computer.getCompany() != null) ? computer
				.getCompany().getName() : "";
		dto.companyId = (computer.getCompany() != null) ? computer.getCompany()
				.getId() : -1;
		return dto;
	}

	public static Computer fromDTO(ComputerDTO dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
		Computer computer = new Computer();
		computer.setId(dto.id);
		computer.setName(dto.name);
		if (!dto.introduced.trim().isEmpty())
			computer.setIntroduced(LocalDateTime.parse(
					dto.introduced += " 00:00:00", formatter));
		if (!dto.discontinued.trim().isEmpty())
			computer.setDiscontinued(LocalDateTime.parse(
					dto.discontinued += " 00:00:00", formatter));
		if (dto.companyId != -1 && !dto.companyName.trim().isEmpty())
			computer.setCompany(new Company(dto.companyId, dto.companyName));
		return computer;
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
		return new StringBuilder("Computer [name : ").append(name)
				.append(" introduced : ").append(introduced)
				.append(" discontinued : ").append(discontinued)
				.append(" company name : ").append(companyName).append(" ]")
				.toString();
	}

}
