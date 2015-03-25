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

	public int getId() {
		return id;
	}

	public int getCompanyId() {
		return companyId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (companyId != other.companyId)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

}
