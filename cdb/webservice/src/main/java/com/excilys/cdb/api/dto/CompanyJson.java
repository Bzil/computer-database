package com.excilys.cdb.api.dto;

import com.excilys.cdb.model.Company;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompanyJson {

	private int id;

	private String name;

	public CompanyJson(int id, String name) {
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

	public static CompanyJson to(Company company) {
		return new CompanyJson(company.getId(), company.getName());
	}

	public static Company from(CompanyJson company) {
		return Company.builder().id(company.id).name(company.name).build();
	}

}
