package com.excilys.cdb.util.sort;

public enum SortColumn {
	NAME("name"), INTRODUCED("introduced"), DISCONTINUED("discontinued"), COMPANY_ID(
			"company_id");

	private String name;

	private SortColumn(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
