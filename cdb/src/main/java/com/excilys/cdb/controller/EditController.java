package com.excilys.cdb.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.dto.ComputerDTO;

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
	protected String load(@RequestParam(required = true) final Integer id,
			@ModelAttribute("computerDto") ComputerDTO computerDto,
			final Model model) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Load edit page  DTO id =" + id);
		}
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
			@Valid @ModelAttribute("computerDto") ComputerDTO computerDto,
			BindingResult result, Model model) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("edit  DTO " + computerDto);
		}
		if (!result.hasErrors()) {
			computerDto.companyName = companyService
					.find(computerDto.companyId) != null ? companyService.find(
							computerDto.companyId).getName() : "";
							final Computer computer = ComputerDTO.fromDTO(computerDto);
							computerService.update(computer);
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("edit computer " + computer);
							}
							return "redirect:/dashboard";
		} else {
			return load(computerDto.id, computerDto, model);
		}
	}
}
