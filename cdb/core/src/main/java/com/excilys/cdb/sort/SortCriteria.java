package com.excilys.cdb.sort;

/**
 * The Class SortCriteria.
 */
public class SortCriteria {

    /**
     * The column.
     */
    private final SortColumn column;

    /**
     * The direction.
     */
    private final SortDirection direction;

    /**
     * Instantiates a new sort criteria.
     *
     * @param column    the column
     * @param direction the direction
     */
    public SortCriteria(SortColumn column, SortDirection direction) {
        super();
        this.column = column;
        this.direction = direction;
    }

    /**
     * Gets the sort criteria.
     *
     * @param column the column
     * @param dir    the sort direction
     * @return the sort criteria
     */
    public static SortCriteria buildSortCriteria(final String column,
                                                 final String dir) {
        SortCriteria sort = null;
        if (column != null && dir != null && !column.trim().isEmpty()
                && !dir.trim().isEmpty()) {
            try {
                sort = new SortCriteria(SortColumn.valueOf(column.trim()
                        .toUpperCase()), SortDirection.valueOf(dir.trim()));
            } catch (final IllegalArgumentException e) {
                sort = null;
            }
        }
        return sort;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ORDER BY computer." + column + " " + direction;
    }

    /**
     * Gets the column.
     *
     * @return the column
     */
    public String getColumn() {
        return column.toString();
    }

    /**
     * Gets the sort column.
     *
     * @return the sort column
     */
    public SortColumn getSortColumn() {
        return column;
    }

    /**
     * Gets the direction.
     *
     * @return the direction
     */
    public String getDirection() {
        return direction.toString();
    }

    /**
     * Gets the sort direction.
     *
     * @return the sort direction
     */
    public SortDirection getSortDirection() {
        return direction;
    }
}
