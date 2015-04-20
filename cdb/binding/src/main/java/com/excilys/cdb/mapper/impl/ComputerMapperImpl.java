package com.excilys.cdb.mapper.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Component
public class ComputerMapperImpl implements ComputerMapper {

	@Override
	public ComputerDTO toDto(Computer t) {
		final DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("dd-MM-yyyy");
		final ComputerDTO dto = new ComputerDTO();
		dto.id = t.getId();
		dto.name = t.getName();
		if (t.getIntroduced() != null) {
			dto.introduced = t.getIntroduced().format(formatter);
		} else {
			dto.introduced = "";
		}
		if (t.getDiscontinued() != null) {
			dto.discontinued = t.getDiscontinued().format(formatter);
		} else {
			dto.discontinued = "";
		}
		if (t.getCompany() != null) {
			dto.companyName = t.getCompany().getName();
		} else {
			dto.companyName = "";
		}
		if (t.getCompany() != null) {
			dto.companyId = t.getCompany().getId();
		} else {
			dto.companyId = -1;
		}
		return dto;
	}

	@Override
	public Computer toModel(ComputerDTO dto) {
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

}
