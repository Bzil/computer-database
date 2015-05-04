/**
 * 
 * @author Basile
 */
package com.excilys.cdb.persistence.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * The Class ComputerJPA.
 */
@Entity
@Table(name = "computer")
public class ComputerJPA {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected Integer id;

	/** The name. */
	@Column(name = "name", nullable = true, length = 255)
	protected String name;

	/** The introduced. */
	@Column(name = "introduced", nullable = true)
	protected Timestamp introduced;

	/** The discontinued. */
	@Column(name = "discontinued", nullable = true)
	protected Timestamp discontinued;

	/** The company. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	protected CompanyJPA company;

	/**
	 * Instantiates a new computer jpa.
	 */
	protected ComputerJPA() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComputerJPA [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}

	/**
	 * To.
	 *
	 * @param computer the computer
	 * @return the computer jpa
	 */
	public static ComputerJPA to(Computer computer) {
		final ComputerJPA computerJPA = new ComputerJPA();
		computerJPA.id = computer.getId();
		computerJPA.name = computer.getName();
		computerJPA.introduced = computer.getIntroduced() != null ? Timestamp.valueOf(computer.getIntroduced()) : null;
		computerJPA.discontinued = computer.getDiscontinued() != null ? Timestamp.valueOf(computer.getDiscontinued())
				: null;
		computerJPA.company = (computer.getCompany() != null) ? CompanyJPA.to(computer.getCompany()) : null;
		return computerJPA;
	}

	/**
	 * From.
	 *
	 * @param computerJPA the computer jpa
	 * @return the computer
	 */
	public static Computer from(ComputerJPA computerJPA) {
		final Computer.Builder builder = Computer.builder(computerJPA.name).id(computerJPA.id);
		if (computerJPA.introduced != null) {
			builder.introduced(computerJPA.introduced.toLocalDateTime());
		}
		if (computerJPA.discontinued != null) {
			builder.introduced(computerJPA.discontinued.toLocalDateTime());
		}
		if (computerJPA.company != null) {
			final Company company = CompanyJPA.from(computerJPA.company);
			builder.company(company);
		}
		return builder.build();
	}
}