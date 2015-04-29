package com.excilys.cdb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.ComputerPage;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.sort.SortCriteria;

@Controller
public class ComputerController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerController.class);

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerMapper mapper;

	/**
	 * Gets the computer list.
	 *
	 * @param id
	 *            the id of the page
	 * @param size
	 *            the size of the page
	 * @param search
	 *            the computer search
	 * @param column
	 *            the column sorted
	 * @param dir
	 *            the dir sorted
	 * @param selection
	 *            the selection to deletion
	 * @param model
	 *            the model
	 * @return the computer list
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getComputerList(
			@RequestParam(required = false) final Integer id,
			@RequestParam(required = false) final Integer size,
			@RequestParam(required = false) final String search,
			@RequestParam(required = false) final String column,
			@RequestParam(required = false) final String dir,
			@RequestParam(required = false) final String selection,
			final Model model) {
		final ComputerPage computerPage = new ComputerPage();

		List<ComputerDTO> entities = null;
		// size of list
		if (size != null && size > 0) {
			computerPage.setOffset(size);
		} else {
			computerPage.setOffset(55);
		}

		// page index
		int page = 1;
		if (id != null && id > 0) {
			page = id;
			computerPage.setCurrentPage(page);
		} else {
			computerPage.setCurrentPage(page);
		}
		// count of cumputer
		long count = computerService.count();

		// Sort criteria
		final SortCriteria criteria = SortCriteria.buildSortCriteria(column,
				dir);
		if (criteria != null) {
			computerPage.setOrderBy(criteria.getDirection());
			computerPage.setColumn(criteria.getColumn());
		}
		// Search
		if (search != null && !search.trim().isEmpty()) {
			LOGGER.info("Looking for : {}", search);
			entities = computerService.find(search, computerPage.getStart(),
					computerPage.getOffset(), criteria);
			count = entities.size();
			computerPage.setSearch(search);
		} else {
			entities = computerService.findAll(computerPage.getStart(),
					computerPage.getOffset(), criteria);
		}
		computerPage.setCount(count);
		computerPage.setEntities(entities);
		LOGGER.info("Show page : {}", computerPage);

		model.addAttribute("page", computerPage);
		return ControllerList.DASHBOARD_VIEW;
	}

	@RequestMapping(value = { "/edit", "/add" }, method = RequestMethod.GET)
	public String load(@RequestParam(required = false) final Integer id,
			@ModelAttribute("computerDto") ComputerDTO computerDto, Model model) {

		final List<CompanyDTO> companies = companyService.findAll(null);
		model.addAttribute("companies", companies);
		// Check param
		if (id != null && id > 0) {
			LOGGER.debug("Load edit page  DTO id = {}", id);
			final ComputerDTO dto = computerService.find(id);
			model.addAttribute("computer", dto);
			return ControllerList.EDIT_VIEW;
		} else {
			LOGGER.debug("Load add page");
			return ControllerList.ADD_VIEW;
		}

	}

	@RequestMapping(value = { "/edit", "/add" }, method = RequestMethod.POST)
	public String edit(
			@Valid @ModelAttribute("computerDto") ComputerDTO computerDto,
			BindingResult result, Model model) {

		LOGGER.debug("add or edit  DTO {}", computerDto);

		if (!result.hasErrors()) {
			computerDto.company.name = companyService
					.find(computerDto.company.id) != null ? companyService
							.find(computerDto.company.id).getName() : "";
							final Computer computer = mapper.toModel(computerDto);
							computerService.saveOrUpdate(computer);
							LOGGER.debug("add or edit computer {}", computer);

							return ControllerList.REDIRECT + ControllerList.DASHBOARD_VIEW;
		} else {
			return load(computerDto.id, computerDto, model);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "selection") String selection) {
		LOGGER.info("Delete {} ", selection);
		// Deletion
		if (selection != null && !selection.trim().isEmpty()) {
			final List<Integer> list = Arrays.stream(selection.split(","))
					.map(Integer::valueOf).collect(Collectors.toList());
			for (final Integer i : list) {
				try {
					computerService.delete(i);
					LOGGER.info("computer with id : {} deleted", i);
				} catch (final Exception e) {
					LOGGER.error("computer with id : {} can not be deleted", i);
				}
			}
		}
		return ControllerList.REDIRECT + ControllerList.DASHBOARD_VIEW;
	}
}
