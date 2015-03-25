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

@WebServlet(urlPatterns = "/dashboard", loadOnStartup = 1)
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ComputerPage computerPage = new ComputerPage();
		int page = 0;
		// count of cumputer
		int count = cs.count();
		computerPage.setCount(count);

		// size of list
		if (request.getParameter("size") != null) {
			computerPage.setOffset(Integer.parseInt(request
					.getParameter("size")));
		} else {
			computerPage.setOffset(55);
		}

		// page id
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			computerPage.setCurrentPage(page);
			computerPage.setStart((Integer.parseInt(request
					.getParameter("page")) - 1) * computerPage.getOffset());
		} else {
			computerPage.setStart(0);
		}

		computerPage.setEntities(cs.findAll(computerPage.getStart(),
				computerPage.getOffset()));

		computerPage.setPageNb(count / computerPage.getOffset());

		request.setAttribute("page", computerPage);
		request.getRequestDispatcher("dashboard.jsp")
				.forward(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
