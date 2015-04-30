package com.excilys.cdb.persistence.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.excilys.cdb.model.Company;

@Entity
@Table(name = "company")
public class CompanyJPA {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private List<ComputerJPA> computers;

	protected CompanyJPA() {
	}

	protected CompanyJPA(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "CompanyJPA{" + "id=" + id + ", name='" + name + '\''
				+ ", computers=" + computers + '}';
	}

	/**
	 * Convert a Company to a CompanyJPA
	 *
	 * @param company
	 *            must not be null
	 * @return the corresponding CompanyJPA
	 */
	public static CompanyJPA to(Company company) {
		return new CompanyJPA(company.getId(), company.getName());
	}

	/**
	 * Convert a Company from a CompanyJPA
	 *
	 * @param company
	 *            must not be null
	 * @return the corresponding Company
	 */
	public static Company from(CompanyJPA company) {
		return Company.builder(company.id, company.name).build();
	}
}
