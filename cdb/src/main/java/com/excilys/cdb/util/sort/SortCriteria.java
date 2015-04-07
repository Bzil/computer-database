package com.excilys.cdb.util.sort;

public class SortCriteria {

	private SortColumn column;
	
	private SortDirection direction;

	public SortCriteria(SortColumn column, SortDirection direction) {
		super();
		this.column = column;
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "ORDER BY " + column + " " + direction;
	}

	public String getColumn() {
		return column.name();
	}

	public String getDirection() {
		return direction.name();
	}
}



