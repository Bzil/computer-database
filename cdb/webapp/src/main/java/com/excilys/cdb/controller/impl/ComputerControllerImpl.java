/**
 *
 * @author Basile
 */
package com.excilys.cdb.controller.impl;

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

import com.excilys.cdb.controller.ComputerController;
import com.excilys.cdb.controller.ControllerList;
import com.excilys.cdb.controller.dto.CompanyView;
import com.excilys.cdb.controller.dto.ComputerView;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.ComputerPage;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.sort.SortCriteria;

/**
 * The Class ComputerControllerImpl.
 */
@Controller
public class ComputerControllerImpl implements ComputerController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

	/** The computer service. */
	@Autowired
	private ComputerService computerService;

	/** The company service. */
	@Autowired
	private CompanyService companyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.controller.ComputerController#getComputerList(java.lang
	 * .Integer, java.lang.Integer, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, org.springframework.ui.Model)
	 */
	@Override
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getComputerList(@RequestParam(required = false) final Integer id,
			@RequestParam(required = false) final Integer size, @RequestParam(required = false) final String search,
			@RequestParam(required = false) final String column, @RequestParam(required = false) final String dir,
			@RequestParam(required = false) final String selection, final Model model) {
		final ComputerPage computerPage = new ComputerPage();

		List<ComputerView> entities = null;
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
		final SortCriteria criteria = SortCriteria.buildSortCriteria(column, dir);
		if (criteria != null) {
			computerPage.setOrderBy(criteria.getDirection());
			computerPage.setColumn(criteria.getColumn());
		}
		// Search
		if (search != null && !search.trim().isEmpty()) {
			LOGGER.info("Looking for : {}", search);
			entities = computerService.find(search, computerPage.getStart(), computerPage.getOffset(), criteria)
					.stream().map(ComputerView::to).collect(Collectors.toList());
			count = entities.size();
			computerPage.setSearch(search);
		} else {
			entities = computerService.findAll(computerPage.getStart(), computerPage.getOffset(), criteria).stream()
					.map(ComputerView::to).collect(Collectors.toList());
		}
		computerPage.setCount(count);
		computerPage.setEntities(entities);
		LOGGER.info("Show page : {}", computerPage);

		model.addAttribute("page", computerPage);
		return ControllerList.DASHBOARD_VIEW;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.controller.ComputerController#load(java.lang.Integer,
	 * com.excilys.cdb.controller.dto.ComputerView,
	 * org.springframework.ui.Model)
	 */
	@Override
	@RequestMapping(value = { "/edit", "/add" }, method = RequestMethod.GET)
	public String load(@RequestParam(required = false) final Integer id,
			@ModelAttribute("computerDto") ComputerView computerDto, Model model) {

		final List<CompanyView> companies = companyService.findAll(null).stream().map(CompanyView::to)
				.collect(Collectors.toList());

		model.addAttribute("companies", companies);
		// Check param
		if (id != null && id > 0) {
			LOGGER.info("Load edit page  DTO id = {}", id);
			final ComputerView dto = ComputerView.to(computerService.find(id));
			model.addAttribute("computer", dto);
			return ControllerList.EDIT_VIEW;
		} else {
			LOGGER.info("Load add page");
			return ControllerList.ADD_VIEW;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.controller.ComputerController#edit(com.excilys.cdb.controller
	 * .dto.ComputerView, org.springframework.validation.BindingResult,
	 * org.springframework.ui.Model)
	 */
	@Override
	@RequestMapping(value = { "/edit", "/add" }, method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("computerDto") ComputerView computerDto, BindingResult result, Model model) {
		LOGGER.info("add or edit  DTO {}", computerDto);

		if (!result.hasErrors()) {
			computerDto.setCompanyName(companyService.find(computerDto.getCompanyId()) != null ? companyService.find(
					computerDto.getCompanyId()).getName() : "");
			final Computer computer = ComputerView.from(computerDto);
			computerService.saveOrUpdate(computer);
			LOGGER.info("add or edit computer {}", computer);

			return ControllerList.REDIRECT + ControllerList.DASHBOARD_VIEW;
		} else {
			return load(computerDto.getId(), computerDto, model);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.controller.ComputerController#delete(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "selection") String selection) {
		LOGGER.info("Delete {} ", selection);
		// Deletion
		if (selection != null && !selection.trim().isEmpty()) {
			final List<Integer> list = Arrays.stream(selection.split(",")).map(Integer::valueOf)
					.collect(Collectors.toList());
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
