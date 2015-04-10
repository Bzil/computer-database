package com.excilys.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.ComputerPage;
import com.excilys.cdb.util.dto.ComputerDTO;
import com.excilys.cdb.util.sort.SortColumn;
import com.excilys.cdb.util.sort.SortCriteria;
import com.excilys.cdb.util.sort.SortDirection;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DashboardController.class);

	@Autowired
	private ComputerService computerService;

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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
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

		// Deletion
		if (selection != null) {
			final List<Integer> list = getSelectionList(selection);
			for (final Integer i : list) {
				try {
					computerService.delete(i);
					LOGGER.info("computer with id : " + i + " deleted");
				} catch (final Exception e) {
					LOGGER.error("computer with id : " + i
							+ " can not be deleted");
				}
			}
		}
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
		String options = "?id=".concat(String.valueOf(page));
		// count of cumputer
		int count = computerService.count();

		// Sort criteria
		final SortCriteria criteria = getSortCriteria(column, dir);
		if (criteria != null) {
			options = options.concat("&column=").concat(criteria.getColumn())
					.concat("&dir=").concat(criteria.getDirection());
			computerPage.setOrderBy(criteria.getDirection());
			computerPage.setColumn(criteria.getColumn());
		}
		// Search
		if (search != null && !search.trim().isEmpty()) {
			LOGGER.info("Looking for : " + search);
			entities = computerService.find(search, criteria);
			count = entities.size();
			options = options.concat("&search=").concat(search);
			computerPage.setSearch(search);
		} else {
			entities = computerService.findAll(computerPage.getStart(),
					computerPage.getOffset(), criteria);
		}
		computerPage.setCount(count);
		computerPage.setEntities(entities);
		LOGGER.info("Show page : " + computerPage);

		model.addAttribute("page", computerPage);
		return "dashboard";// + options;
	}

	/**
	 * Gets the sort criteria.
	 *
	 * @param column
	 *            the column
	 * @param dir
	 *            the sort direction
	 * @return the sort criteria
	 */
	private SortCriteria getSortCriteria(final String column, final String dir) {

		SortCriteria sort = null;
		if (column != null && dir != null && !column.trim().isEmpty()
				&& !dir.trim().isEmpty()) {
			try {
				sort = new SortCriteria(SortColumn.valueOf(column.trim()
						.toUpperCase()), SortDirection.valueOf(dir.trim()));
			} catch (final IllegalArgumentException e) {
				sort = null;
			}
		}
		return sort;
	}

	/**
	 * Gets the selection list.
	 *
	 * @param selection
	 *            the selection
	 * @return the selection list
	 */
	private List<Integer> getSelectionList(final String selection) {
		List<Integer> ret = null;
		if (selection != null && !selection.equals("0")) {
			ret = new ArrayList<>();
			for (final String s : selection.trim().split(",")) {
				ret.add(Integer.valueOf(s));
			}
		}
		return ret;

	}
}
