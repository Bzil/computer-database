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

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.dto.ComputerDTO;

@Controller
@RequestMapping("add")
public class AddController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddController.class);

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(@ModelAttribute("computerDto") ComputerDTO computerDto,
			Model model) {
		final List<CompanyDTO> companies = companyService.findAll(null);
		model.addAttribute("companies", companies);
		return ControllerList.ADD_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(
			@Valid @ModelAttribute("computerDto") ComputerDTO computerDto,
			BindingResult result, Model model) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Load add page  DTO " + computerDto);
		}
		if (!result.hasErrors()) {
			computerDto.companyName = companyService
					.find(computerDto.companyId) != null ? companyService.find(
							computerDto.companyId).getName() : "";
							final Computer computer = ComputerDTO.fromDTO(computerDto);
							computerService.add(computer);
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("add computer " + computer);
							}
		}
		return load(computerDto, model);

	}
}
