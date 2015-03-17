package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyService {
	
public Company find(int id);
	
	public List<Company> findAll();
	
	public Company add(Company company);
	
	public void delete(Company company);
	
	public Company update(Company company);
}
