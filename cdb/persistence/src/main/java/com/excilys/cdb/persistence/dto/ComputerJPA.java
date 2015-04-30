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

import com.excilys.cdb.model.Computer;

@Entity
@Table(name = "computer")
public class ComputerJPA {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected Integer id;

	@Column(name = "name", nullable = true, length = 255)
	protected String name;

	@Column(name = "introduced", nullable = true)
	protected Timestamp introduced;

	@Column(name = "discontinued", nullable = true)
	protected Timestamp discontinued;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	protected CompanyJPA company;

	protected ComputerJPA() {
	}

	@Override
	public String toString() {
		return "ComputerJPA [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}

	/**
	 * Convert a Computer to the corresponding ComputerJPA
	 *
	 * @param computer
	 *            must not be null
	 * @return the corresponding ComputerJPA
	 */
	public static ComputerJPA to(Computer computer) {
		final ComputerJPA computerJPA = new ComputerJPA();
		computerJPA.id = computer.getId();
		computerJPA.name = computer.getName();
		computerJPA.introduced = computer.getIntroduced() != null ? Timestamp.valueOf(computer.getIntroduced()) : null;
		computerJPA.discontinued = computer.getDiscontinued() != null ? Timestamp.valueOf(computer.getDiscontinued())
				: null;
		computerJPA.company = computer.getCompany() != null ? CompanyJPA.to(computer.getCompany()) : null;
		return computerJPA;
	}

	/**
	 * Convert a ComputerJPA to the corresponding Computer
	 *
	 * @param computerJPA
	 *            must not be null
	 * @return the corresponding Computer
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
			builder.company(CompanyJPA.from(computerJPA.company));
		}
		return builder.build();
	}
}