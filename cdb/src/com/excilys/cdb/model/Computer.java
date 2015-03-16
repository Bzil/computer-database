package com.excilys.cdb.model;

import java.util.Date;

/**
 * The Class Computer.
 */
public class Computer {
	
	/** Specific id of a computer. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The introduced. */
	private Date introduced;
	
	/** The discontinued. */
	private Date discontinued;
	
	/** The compagny. */
	private Integer companyId;
	
	/**
	 * Instantiates a new computer.
	 *
	 * @param id the id
	 * @param name the name
	 * @param introduced the introducedgetTime
	 * @param discontinued the discontinued
	 * @param companyId the company id
	 */
	public Computer(int id, String name, Date introduced, Date discontinued, int companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	public Computer(String name, Date introduced, Date discontinued, int companyId) {
		this(-1, name, introduced, discontinued, companyId);
	}

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
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public Date getIntroduced() {
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
	 * @param name the new name
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
	 * @param companyId the new company id
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced the new introduced
	 */
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public Date getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder("Computer [id=").append(id)
				.append(" name : ").append(name)
				.append(" introduced : ").append(introduced)
				.append(" discontinued : ").append(discontinued)
				.append(" company id : ").append(companyId)
				.append(" ]").toString();
	}
}
