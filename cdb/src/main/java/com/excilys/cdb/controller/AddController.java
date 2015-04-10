package com.excilys.cdb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.validator.Validator;

@Controller
@RequestMapping("add")
public class AddController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddController.class);

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	/**
	 * Adds the.
	 *
	 * @param name
	 *            the name
	 * @param introducedS
	 *            the introduced s
	 * @param discontinuedS
	 *            the discontinued s
	 * @param companyId
	 *            the company id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String add(
			@RequestParam(required = false) final String computerName,
			@RequestParam(required = false) final String introduced,
			@RequestParam(required = false) final String discontinued,
			@RequestParam(required = false) final Integer companyId,
			final Model model) {

		if (computerName != null) {
			if (!computerName.trim().isEmpty()) {
				final LocalDateTime introducedDate = getLocalDateTime(introduced);
				final LocalDateTime discontinuedDate = getLocalDateTime(discontinued);
				final CompanyDTO company = companyService.find(companyId);

				final Computer computer = new Computer(computerName);
				computer.setIntroduced(introducedDate);
				computer.setDiscontinued(discontinuedDate);
				computer.setCompany(company == null ? null : CompanyDTO
						.fromDTO(company));
				LOGGER.info("add computer " + computer);
				computerService.add(computer);
				model.addAttribute("add", "Success");
			} else {
				model.addAttribute("add", "Fail");
				return "500";
			}
		}

		final List<CompanyDTO> companies = companyService.findAll(null);
		model.addAttribute("companies", companies);
		return "addComputer";
	}

	private LocalDateTime getLocalDateTime(String str) {
		LocalDateTime date = null;
		if (Validator.getInstance().isCorrectDate(str)) {
			str = str.trim();
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
					"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
			str += " 00:00:00";
			date = LocalDateTime.parse(str, formatter);
		}
		return date;
	}
}
