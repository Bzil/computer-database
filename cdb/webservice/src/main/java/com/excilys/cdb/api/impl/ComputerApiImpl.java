package com.excilys.cdb.api.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.api.ComputerApi;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.sort.SortColumn;
import com.excilys.cdb.sort.SortCriteria;
import com.excilys.cdb.sort.SortDirection;

/**
 * The Class ComputerApiImpl.
 */
@Path("/computer")
public class ComputerApiImpl implements ComputerApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerApiImpl.class);

	/** The computer service. */
	@Autowired
	private ComputerService computerService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getById(java.lang.Integer)
	 */
	@Override
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Integer id) {
		LOGGER.debug("JSON to computer {}", id);
		final ComputerDTO dto = computerService.find(id);
		return dto != null ? Response.ok(dto).build() : Response.noContent()
				.build();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getAll()
	 */
	@Override
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		LOGGER.debug("JSON getAll");
		final List<ComputerDTO> dtos = computerService.findAll(null);
		return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
				: Response.noContent().build();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getAllByCriteria(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@GET
	@Path("list/{name}/{order}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByCriteria(@PathParam("name") final String name,
			@PathParam("order") String order) {
		LOGGER.debug("JSON get all : column {}, order {}", name, order);
		final SortCriteria criteria = getSortCriteria(name, order);
		if (criteria == null) {
			return Response.status(Status.BAD_REQUEST).allow(getAllowParam())
					.build();
		} else {
			final List<ComputerDTO> dtos = computerService.findAll(criteria);
			return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
					: Response.noContent().build();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.api.ComputerApi#getByName(java.lang.String)
	 */
	@Override
	@GET
	@Path("name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@PathParam("name") final String name) {
		LOGGER.debug("JSON to computer {}", name);
		final List<ComputerDTO> dtos = computerService.find(name, null);
		return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
				: Response.noContent().build();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getPage(java.lang.String,
	 * java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@GET
	@Path("list/{name}/{order}/{start}/{offset}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPage(@PathParam("name") final String name,
			@PathParam("order") String order,
			@PathParam("start") Integer start,
			@PathParam("offset") Integer offset) {
		LOGGER.debug("JSON get all : column {}, order {}, start {}, offest {}",
				name, order, start, offset);
		final SortCriteria criteria = getSortCriteria(name, order);
		if (criteria == null) {
			return Response.status(Status.BAD_REQUEST).allow(getAllowParam())
					.build();
		} else {
			final List<ComputerDTO> dtos = computerService.findAll(start,
					offset, criteria);
			return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
					: Response.noContent().build();
		}
	}

	/**
	 * Gets the allow param.
	 *
	 * @return the allow param
	 */
	private Set<String> getAllowParam() {
		final Set<String> ret = new HashSet<String>();
		ret.add("Name : name | introduced | discontinued | company_id");
		ret.add("Order : asc | desc");
		return ret;
	}

	// TODO delete duplicate code
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
}
