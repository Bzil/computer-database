package com.excilys.cdb.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.util.ComputerPage;
import com.excilys.cdb.util.validator.Validator;

@WebServlet(urlPatterns = "/dashboard", loadOnStartup=1)
public class DashBoardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ComputerService cs;

	public void init(ServletConfig config) {
		cs = ComputerServiceImpl.INSTANCE.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ComputerPage computerPage = new ComputerPage();
		int page = 0;
		// count of cumputer
		int count = cs.count();
		computerPage.setCount(count);

		// size of list
		if (Validator.INSTANCE.isNumericString(request.getParameter("size"))) {
			computerPage.setOffset(Integer.parseInt(request
					.getParameter("size")));
		} else {
			computerPage.setOffset(55);
		}

		// page id
		if (Validator.INSTANCE.isNumericString(request.getParameter("id"))) {
			page = Integer.parseInt(request.getParameter("id"));
			computerPage.setCurrentPage(page);
			computerPage.setStart((Integer.parseInt(request
					.getParameter("id")) - 1) * computerPage.getOffset());
		} else {
			computerPage.setStart(0);
		}

		computerPage.setEntities(cs.findAll(computerPage.getStart(),
				computerPage.getOffset()));

		computerPage.setPageNb(count / computerPage.getOffset());

		request.setAttribute("page", computerPage);
		request.getRequestDispatcher(ControllerServlet.DASHBOARD_JSP)
				.forward(request, response);
	}
}
