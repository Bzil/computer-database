package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.util.ComputerPage;
import com.excilys.cdb.util.dto.ComputerDTO;
import com.excilys.cdb.util.sort.SortColumn;
import com.excilys.cdb.util.sort.SortCriteria;
import com.excilys.cdb.util.sort.SortDirection;
import com.excilys.cdb.util.validator.Validator;

@WebServlet(urlPatterns = "/dashboard", loadOnStartup = 1)
public class DashBoardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DashBoardServlet.class);

	private ComputerService cs;

	public void init(ServletConfig config) {
		cs = ComputerServiceImpl.INSTANCE.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ComputerPage computerPage = new ComputerPage();
		List<ComputerDTO> entities = null;

		// Deletion
		if (request.getParameter("selection") != null) {
			List<Integer> list = getSelectionList(request);
			for (Integer i : list) {
				try {
					ComputerServiceImpl.INSTANCE.delete(i);
					LOGGER.info("computer with id : " + i + " deleted");
				} catch (Exception e) {
					LOGGER.error("computer with id : " + i
							+ " can not be deleted");
				}
			}
		}

		// size of list
		if (Validator.INSTANCE.isNumericString(request.getParameter("size"))) {
			computerPage.setOffset(Integer.parseInt(request
					.getParameter("size")));
		} else {
			computerPage.setOffset(55);
		}

		// page index
		int page = 1;
		if (Validator.INSTANCE.isNumericString(request.getParameter("id"))) {
			page = Integer.parseInt(request.getParameter("id"));
			computerPage.setCurrentPage(page);
		} else {
			computerPage.setCurrentPage(page);
		}
		String options = "?id=".concat(String.valueOf(page));
		// count of cumputer
		int count = cs.count();

		// Sort criteria
		SortCriteria criteria = getSortCriteria(request);
		if (criteria != null) {
			options = options.concat("&column=").concat(criteria.getColumn())
					.concat("&dir=").concat(criteria.getDirection());
			computerPage.setOrderBy(criteria.getDirection());
			computerPage.setColumn(criteria.getColumn());
		}
		// Search
		if (request.getParameter("search") != null && !request.getParameter("search").trim().isEmpty()) {
			String search = request.getParameter("search");
			LOGGER.info("Looking for : " + search);
			entities = cs.find(search, criteria);
			count = entities.size();
			options = options.concat("&search=").concat(search);
			computerPage.setSearch(search);
		} else {
			entities = cs.findAll(computerPage.getStart(),
					computerPage.getOffset(), criteria);
		}

		computerPage.setCount(count);
		computerPage.setEntities(entities);
		LOGGER.info("Show page : " + computerPage);
		request.setAttribute("page", computerPage);
		request.getRequestDispatcher(
				ServletList.DASHBOARD_JSP.toString() + options).forward(
				request, response);
	}

	private List<Integer> getSelectionList(HttpServletRequest request) {
		List<Integer> ret = null;
		String selection = request.getParameter("selection");
		if (!selection.equals("0")) {
			ret = new ArrayList<>();
			for (String s : selection.split(",")) {
				ret.add(Integer.valueOf(s));
			}
		}
		return ret;

	}

	private SortCriteria getSortCriteria(HttpServletRequest request) {
		String column = request.getParameter("column");
		String dir = request.getParameter("dir");

		SortCriteria sort = null;
		if (column != null && dir != null && !column.trim().isEmpty()
				&& !dir.trim().isEmpty()) {
			try {
				sort = new SortCriteria(SortColumn.valueOf(column.trim()
						.toUpperCase()), SortDirection.valueOf(dir.trim()));
			} catch (IllegalArgumentException e) {
				sort = null;
			}
		}
		return sort;
	}
}
