package com.excilys.cdb.util.sort;

public enum SortDirection {
	ASC("ASC"), DESC("DESC");

	private String name;

	private SortDirection(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
