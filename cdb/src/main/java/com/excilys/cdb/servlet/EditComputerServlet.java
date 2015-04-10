package com.excilys.cdb.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.dto.ComputerDTO;
import com.excilys.cdb.util.validator.Validator;

@WebServlet(urlPatterns = "/edit")
public class EditComputerServlet extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EditComputerServlet.class);
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = -1;

		// Check param
		if (Validator.INSTANCE.isNumericString(request.getParameter("id"))) {
			id = Integer.parseInt(request.getParameter("id"));
		}

		if (id > 0) {
			ComputerDTO dto = computerService.find(id);

			List<CompanyDTO> companies = companyService.findAll(null);

			request.setAttribute("computer", dto);
			request.setAttribute("companies", companies);
			request.getRequestDispatcher(ServletList.EDIT_JSP.toString()).forward(
					request, response);
		}

		else {
			request.getRequestDispatcher(ServletList.ERROR_500_JSP.toString())
					.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("computerName") != null) {
			int id = Integer.parseInt(request.getParameter("id").trim());
			String name = request.getParameter("computerName").trim();
			String introducedString = request.getParameter("introduced").trim();
			String discontinuedString = request.getParameter("discontinued")
					.trim();
			int companyId = Integer.parseInt(request.getParameter("companyId")
					.trim());

			LocalDateTime introduced = getLocalDateTime(introducedString);
			LocalDateTime discontinued = getLocalDateTime(discontinuedString);
			CompanyDTO company = companyService.find(companyId);

			Computer computer = new Computer(id, name, introduced,
					discontinued, CompanyDTO.fromDTO(company));
			LOGGER.info("add update " + computer);
			computerService.update(computer);
			request.getRequestDispatcher(ServletList.DASHBOARD_SERV.toString())
					.forward(request, response);
		} else {
			request.getRequestDispatcher(ServletList.ERROR_500_JSP.toString())
					.forward(request, response);
		}
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

	protected void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

	protected void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	

}
