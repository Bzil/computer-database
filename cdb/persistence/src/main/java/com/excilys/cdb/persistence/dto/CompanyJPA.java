/**
 * 
 * @author Basile
 */
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

/**
 * The Class CompanyJPA.
 */
@Entity
@Table(name = "company")
public class CompanyJPA {

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;

	/** The name. */
	@Column(name = "name", nullable = true, length = 255)
	protected String name;

	/** The computers. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	protected List<ComputerJPA> computers;

	/**
	 * Instantiates a new company jpa.
	 */
	protected CompanyJPA() {
	}

	/**
	 * Instantiates a new company jpa.
	 *
	 * @param id the id
	 * @param name the name
	 */
	protected CompanyJPA(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CompanyJPA {id=%d, name='%s' }", id, name);
	}

	/**
	 * To.
	 *
	 * @param company the company
	 * @return the company jpa
	 */
	public static CompanyJPA to(Company company) {
		return new CompanyJPA(company.getId(), company.getName());
	}

	/**
	 * From.
	 *
	 * @param companyJPA the company jpa
	 * @return the company
	 */
	public static Company from(CompanyJPA companyJPA) {
		return (companyJPA != null ) ? Company.builder().id(companyJPA.getId()).name(companyJPA.getName()).build() : null;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
