package com.excilys.cdb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = {"/controller"}) 	
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ControllerServlet.class);
	
	private static final String PAGE = "page";

	public static final String DASHBOARD_JSP = "dashboard.jsp";
	public static final String DASHBOARD_SERV = "dashboard";

	public static final String EDIT_JSP = "editComputer.jsp";
	public static final String EDIT_SERV = "editComputer";

	public static final String ADD_JSP = "addComputer.jsp";
	public static final String ADD_SERV = "addComputer";

	public static final String ERROR_404_JSP = "404.jsp";

	public static final String ERROR_403_JSP = "403.jsp";

	public static final String ERROR_500_JSP = "500.jsp";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = DASHBOARD_SERV;

		if (request.getParameter(PAGE) == null) {
			LOGGER.info("Redirect to 404 error");
			forward = ERROR_404_JSP;
		} else {
			if (request.getParameter(PAGE).equals("edit")) {
				LOGGER.info("Redirect to Edition servlet");
				forward = EDIT_SERV;
			}
			if (request.getParameter(PAGE).equals("add")) {
				LOGGER.info("Redirect to Add servlet");
				forward = ADD_SERV;
			}
			LOGGER.info("Show dahsboard");
		}
		
		request.getRequestDispatcher(forward).forward(request, response);
	}
}
