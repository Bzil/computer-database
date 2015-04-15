package com.excilys.cdb.util.page;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.util.dto.CompanyDTO;

public class CompanyPage extends Page<Company, CompanyDTO> {

	@Override
	protected CompanyDTO getDTO(Company computer) {
		return CompanyDTO.toDTO(computer);
	}

}
