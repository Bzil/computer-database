package com.excilys.cdb.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.validator.Validator;

@WebServlet(urlPatterns = "/addComputer")
public class AddComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;
	private CompanyService companyService;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		companyService = CompanyServiceImpl.INSTANCE.getInstance();
		if (request.getParameter("computerName") != null) {
			computerService = ComputerServiceImpl.INSTANCE.getInstance();
			String name = request.getParameter("computerName").trim();
			String introducedString = request.getParameter("introduced").trim();
			String discontinuedString = request.getParameter("discontinued")
					.trim();
			int companyId = Integer.parseInt(request.getParameter("companyId")
					.trim());

			LocalDateTime introduced = getLocalDateTime(introducedString);
			LocalDateTime discontinued = getLocalDateTime(discontinuedString);
			Company company = companyService.find(companyId);

			Computer computer = new Computer(name, introduced, discontinued,
					company);

			computerService.add(computer);
		}
		
		List<Company> companies = companyService.findAll();
		request.setAttribute("companies", companies);
		
		request.getRequestDispatcher("addComputer.jsp").forward(request,
				response);

	}

	private LocalDateTime getLocalDateTime(String str) {
		LocalDateTime date = null;
		if (Validator.getInstance().validateDate(str)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
					"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
			str += " 00:00:00";
			date = LocalDateTime.parse(str, formatter);
		}
		return date;
	}
}
