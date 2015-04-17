package com.excilys.cdb.sort;

/**
 * The Enum SortColumn.
 */
public enum SortColumn {

	/** The name. */
	NAME("name"),
	/** The introduced. */
	INTRODUCED("introduced"),
	/** The discontinued. */
	DISCONTINUED("discontinued"),
	/** The company id. */
	COMPANY_ID("company_id");

	/** The name. */
	private String name;

	/**
	 * Instantiates a new sort column.
	 *
	 * @param name
	 *            the name
	 */
	private SortColumn(String name) {
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
