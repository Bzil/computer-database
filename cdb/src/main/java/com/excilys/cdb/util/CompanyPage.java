package com.excilys.cdb.util;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.util.dto.CompanyDTO;

public class CompanyPage extends Page<Company> {

	@Override
	protected CompanyDTO getDTO(Company computer) {
		return CompanyDTO.toDTO(computer);
	}

	@Override
	protected CompanyService getService() {
		return CompanyServiceImpl.INSTANCE.getInstance();
	}

}
