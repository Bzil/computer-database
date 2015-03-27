package com.excilys.cdb.util.sort;

public enum SortColumn {
	NAME("name"),
	INTRODUCED("introduced"),
	DISONTINUED("discontinued"), 
	COMPANY("company_id");
	
	private String name;
	
	private SortColumn(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
