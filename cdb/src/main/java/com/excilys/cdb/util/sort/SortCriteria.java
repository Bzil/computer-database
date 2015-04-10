package com.excilys.cdb.util.sort;

/**
 * The Class SortCriteria.
 */
public class SortCriteria {

	/** The column. */
	private SortColumn column;
	
	/** The direction. */
	private SortDirection direction;

	/**
	 * Instantiates a new sort criteria.
	 *
	 * @param column the column
	 * @param direction the direction
	 */
	public SortCriteria(SortColumn column, SortDirection direction) {
		super();
		this.column = column;
		this.direction = direction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ORDER BY compu." + column + " " + direction;
	}

	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public String getColumn() {
		return column.name();
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public String getDirection() {
		return direction.name();
	}
}



