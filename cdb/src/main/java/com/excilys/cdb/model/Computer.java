package com.excilys.cdb.model;

import java.time.LocalDateTime;


/**
 * The Class Computer.
 */
public class Computer {

	/** Specific id of a computer. */
	private int id;

	/** The name. */
	private String name;

	/** The introduced. */
	private LocalDateTime introduced;

	/** The discontinued. */
	private LocalDateTime discontinued;

	/** The compagny. */
	private int companyId;

	/**
	 * Instantiates a new computer.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introducedgetTime
	 * @param discontinued
	 *            the discontinued
	 * @param companyId
	 *            the company id
	 */
	public Computer(int id, String name, LocalDateTime introduced, LocalDateTime discontinued,
			int companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public Computer(String name, LocalDateTime introduced, LocalDateTime discontinued,
			int companyId) {
		this(-1, name, introduced, discontinued, companyId);
	}
	
	public Computer(String name){
		this.name = name;
	}
	public Computer() {}

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
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public LocalDateTime getIntroduced() {
		return introduced;
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

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId
	 *            the new company id
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced
	 *            the new introduced
	 */
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued
	 *            the new discontinued
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder("Computer [id=").append(id).append(" name : ")
				.append(name).append(" introduced : ").append(introduced)
				.append(" discontinued : ").append(discontinued)
				.append(" company id : ").append(companyId).append(" ]")
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (companyId != other.companyId)
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
}