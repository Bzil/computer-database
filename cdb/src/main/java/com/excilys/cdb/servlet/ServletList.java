package com.excilys.cdb.servlet;

public enum ServletList {

	DASHBOARD_JSP("WEB-INF/view/dashboard.jsp"), DASHBOARD_SERV("dashboard"),

	EDIT_JSP("WEB-INF/view/editComputer.jsp"), EDIT_SERV("edit"),

	ADD_JSP("WEB-INF/view/addComputer.jsp"), ADD_SERV("add"),

	ERROR_404_JSP("WEB-INF/view/404.jsp"),

	ERROR_403_JSP("WEB-INF/view/403.jsp"),

	ERROR_500_JSP("WEB-INF/view/500.jsp");

	public String name;

	private ServletList(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
