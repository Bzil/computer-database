/**
 * 
 * @author Basile
 */
package com.excilys.cdb.sort;

/**
 * The Enum SortDirection.
 */
public enum SortDirection {

	/** The asc. */
	ASC("ASC"),
	
	/** The desc. */
	DESC("DESC");

	/** The name. */
	private String name;

	/**
	 * Instantiates a new sort direction.
	 *
	 * @param name the name
	 */
	SortDirection(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

}
