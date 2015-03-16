package com.excilys.cdb.model;

/**
 * The Class Company.
 */
public class Company {
	
	/** Specific id of a company. */
	private int id;
	
	/** company name. */
	private String name;
	
	
	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Company(String name) {
		this(-1, name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
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
	public void setId(int id) {
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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
