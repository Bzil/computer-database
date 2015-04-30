package com.excilys.cdb.controller.dto;

import com.excilys.cdb.model.Company;

public class CompanyView {

	private int id;

	private String name;

	public CompanyView(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static CompanyView to(Company company) {
		return new CompanyView(company.getId(), company.getName());
	}

	public static Company from(CompanyView company) {
		return Company.builder(company.id, company.name).build();
	}

}
