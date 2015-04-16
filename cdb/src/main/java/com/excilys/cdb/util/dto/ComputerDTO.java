package com.excilys.cdb.util.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.validation.annotation.Date;

public class ComputerDTO implements DTO<Computer>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public int id;

	@NotEmpty(message = "{validation.empty}")
	@NotBlank(message = "{validation.blank.char}")
	@Size(min = 3, max = 100)
	@Pattern(message = "{validation.start.blank}", regexp = "^(?![ ]+).*$")
	public String name;

	@Date
	public String introduced;

	@Date
	public String discontinued;

	public int companyId;

	public String companyName;

	public static ComputerDTO toDTO(final Computer computer) {
		final DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("dd-MM-yyyy");
		final ComputerDTO dto = new ComputerDTO();
		dto.id = computer.getId();
		dto.name = computer.getName();
		if (computer.getIntroduced() != null) {
			dto.introduced = computer.getIntroduced().format(formatter);
		} else {
			dto.introduced = "";
		}
		if (computer.getDiscontinued() != null) {
			dto.discontinued = computer.getDiscontinued().format(formatter);
		} else {
			dto.discontinued = "";
		}
		if (computer.getCompany() != null) {
			dto.companyName = computer.getCompany().getName();
		} else {
			dto.companyName = "";
		}
		if (computer.getCompany() != null) {
			dto.companyId = computer.getCompany().getId();
		} else {
			dto.companyId = -1;
		}
		return dto;
	}

	public static Computer fromDTO(final ComputerDTO dto) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
		final Computer computer = new Computer();

		computer.setId(dto.id);
		computer.setName(dto.name);
		if (dto.introduced != null && !dto.introduced.trim().isEmpty()) {
			computer.setIntroduced(LocalDateTime.parse(
					dto.introduced += " 00:00:00", formatter));
		}
		if (dto.discontinued != null && !dto.discontinued.trim().isEmpty()) {
			computer.setDiscontinued(LocalDateTime.parse(
					dto.discontinued += " 00:00:00", formatter));
		}
		if (dto.companyId != -1 && !dto.companyName.trim().isEmpty()) {
			computer.setCompany(new Company(dto.companyId, dto.companyName
					.trim()));
		}
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

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ComputerDTO other = (ComputerDTO) obj;
		if (companyId != other.companyId) {
			return false;
		}
		if (companyName == null) {
			if (other.companyName != null) {
				return false;
			}
		} else if (!companyName.equals(other.companyName)) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
