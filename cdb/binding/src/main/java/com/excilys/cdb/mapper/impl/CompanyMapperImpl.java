package com.excilys.cdb.mapper.impl;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;

@Component
public class CompanyMapperImpl implements CompanyMapper {

	@Override
	public CompanyDTO toDto(Company t) {
		final CompanyDTO dto = new CompanyDTO();
		dto.id = t.getId();
		dto.name = t.getName();
		return dto;
	}

	@Override
	public Company toModel(CompanyDTO dto) {
		return new Company(dto.id, dto.name);
	}

}
