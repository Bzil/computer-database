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
import com.excilys.cdb.util.dto.ComputerDTO;
import com.excilys.cdb.util.validator.Validator;

@Controller
@RequestMapping("edit")
public class EditController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EditController.class);

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	protected String getParam(@RequestParam(required = true) final Integer id,
			final Model model) {

		// Check param
		if (id != null && id > 0) {
			final ComputerDTO dto = computerService.find(id);
			final List<CompanyDTO> companies = companyService.findAll(null);

			model.addAttribute("computer", dto);
			model.addAttribute("companies", companies);
		} else {
			return "500";
		}
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String edit(
			@RequestParam(required = false) final String computerName,
			@RequestParam(required = false) final Integer id,
			@RequestParam(required = false) final String introduced,
			@RequestParam(required = false) final String discontinued,
			@RequestParam(required = false) final Integer companyId,
			final Model model) {
		if (computerName != null && !computerName.trim().isEmpty()) {
			final LocalDateTime introducedDate = getLocalDateTime(introduced);
			final LocalDateTime discontinuedDate = getLocalDateTime(discontinued);
			final CompanyDTO company = companyService.find(companyId);

			final Computer computer = new Computer(id, computerName.trim(),
					introducedDate, discontinuedDate,
					CompanyDTO.fromDTO(company));
			LOGGER.info("add update " + computer);
			computerService.update(computer);
			return "redirect:/dashboard";
		} else {
			return "500";
		}
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
