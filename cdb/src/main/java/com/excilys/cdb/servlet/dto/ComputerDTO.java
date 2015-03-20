package com.excilys.cdb.servlet.dto;

import java.time.LocalDateTime;

import com.excilys.cdb.model.Computer;

public class ComputerDTO implements DTO<Computer>{

	public int id;
	public String name;
	public LocalDateTime introduced;
	public LocalDateTime discontinued;
	public int companyId;

	public static ComputerDTO toDTO(Computer computer) {
		ComputerDTO dto = new ComputerDTO();
		dto.id = computer.getId();
		dto.name = computer.getName();
		dto.introduced = computer.getIntroduced();
		dto.discontinued = computer.getDiscontinued();
		dto.companyId = computer.getCompanyId();
		return dto;
	}

}
