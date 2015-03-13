package com.excilys.cdb.model;

import java.time.LocalDate;

/**
 * The Class Computer.
 */
public class Computer {
	
	/** Specific id of a computer. */
	private int id;
	
	/** The introduced. */
	private LocalDate introduced;
	
	/** The discontinued. */
	private LocalDate discontinued;
	
	/** The compagny. */
	private Company company;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public LocalDate getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced the new introduced
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the compagny.
	 *
	 * @return the compagny
	 */
	public Company getCompagny() {
		return company;
	}

	/**
	 * Sets the compagny.
	 *
	 * @param company the new compagny
	 */
	public void setCompagny(Company company) {
		this.company = company;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Computer [id=" + id + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + ", compagny=" + company
				+ "]";
	}
}
