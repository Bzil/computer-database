package com.excilys.cdb.util.dto;

import com.excilys.cdb.model.Company;

public class CompanyDTO implements DTO<Company>{

	public int id;
	public String name;

	public static CompanyDTO toDTO(Company company) {
		CompanyDTO dto = new CompanyDTO();
		dto.id = company.getId();
		dto.name = company.getName();
		return dto;
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
}
