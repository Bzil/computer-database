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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "company")
public class CompanyJPA {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;

	@Column(name = "name", nullable = true, length = 255)
    protected String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    protected List<ComputerJPA> computers;

	protected CompanyJPA() {
	}

	protected CompanyJPA(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("CompanyJPA {id=%d, name='%s' }", id, name);
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
	 * @param companyJPA
	 *            must not be null
	 * @return the corresponding Company
	 */
	public static Company from(CompanyJPA companyJPA) {
        return Company.builder().id(companyJPA.getId()).name(companyJPA.getName()).build();
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
