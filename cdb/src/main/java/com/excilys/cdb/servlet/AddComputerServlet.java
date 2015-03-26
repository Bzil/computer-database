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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.validator.Validator;

@WebServlet(urlPatterns = "/add")
public class AddComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddComputerServlet.class);

	private ComputerService computerService;
	private CompanyService companyService;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		companyService = CompanyServiceImpl.INSTANCE.getInstance();
		if (request.getParameter("computerName") != null) {
			String name = request.getParameter("computerName").trim();
			if (!name.isEmpty()) {
				computerService = ComputerServiceImpl.INSTANCE.getInstance();
				String introducedString = request.getParameter("introduced")
						.trim();
				String discontinuedString = request
						.getParameter("discontinued").trim();
				int companyId = Integer.parseInt(request.getParameter(
						"companyId").trim());

				LocalDateTime introduced = getLocalDateTime(introducedString);
				LocalDateTime discontinued = getLocalDateTime(discontinuedString);
				CompanyDTO company = companyService.find(companyId);

				Computer computer = new Computer(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompany(company == null ? null : CompanyDTO
						.fromDTO(company));
				LOGGER.info("add computer " + computer);
				computerService.add(computer);
			} else {
				request.getRequestDispatcher(ControllerServlet.ERROR_500_JSP)
						.forward(request, response);
			}
		}

		List<CompanyDTO> companies = companyService.findAll();
		request.setAttribute("companies", companies);
		request.getRequestDispatcher(ControllerServlet.ADD_JSP).forward(
				request, response);

	}

	private LocalDateTime getLocalDateTime(String str) {
		LocalDateTime date = null;
		if (Validator.getInstance().isCorrectDate(str)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
					"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
			str += " 00:00:00";
			date = LocalDateTime.parse(str, formatter);
		}
		return date;
	}
}
