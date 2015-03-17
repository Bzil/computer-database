package com.excilys.cdb.service.impl;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.service.CompanyService;

public enum CompanyServiceImpl implements CompanyService {
	INSTANCE;
	
	private CompanyDao dao;
	
	private CompanyServiceImpl(){
		dao = CompanyDaoImpl.INSTANCE.getInstance();
	}
	
	public CompanyService getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Company find(int id) {
		return dao.find(id);
	}

	@Override
	public List<Company> findAll() {
		return dao.findAll();
	}

	@Override
	public Company add(Company company) {
		return dao.create(company);
	}

	@Override
	public void delete(Company company) {
		dao.delete(company);

	}

	@Override
	public Company update(Company company) {
		return dao.update(company);
	}

}
