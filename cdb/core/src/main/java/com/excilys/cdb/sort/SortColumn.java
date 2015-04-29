package com.excilys.cdb.sort;

/**
 * The Enum SortColumn.
 */
public enum SortColumn {

    /**
     * The name.
     */
    NAME("computer.name"),
    /**
     * The introduced.
     */
    INTRODUCED("computer.introduced"),
    /**
     * The discontinued.
     */
    DISCONTINUED("computer.discontinued"),
    /**
     * The company id.
     */
    COMPANY_ID("computer.company.id");

    /**
     * The name.
     */
    private String name;

    /**
     * Instantiates a new sort column.
     *
     * @param name the name
     */
    SortColumn(String name) {
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
