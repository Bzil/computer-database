package com.excilys.cdb.api.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.service.ComputerService;

/**
 * The Class ComputerApiImpl.
 */
@Path("/computer")
@Produces(MediaType.APPLICATION_JSON)
public class ComputerApiImpl implements ComputerApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerApiImpl.class);

	/** The computer service. */
	@Autowired
	private ComputerService computerService;

	@Autowired
	private ComputerMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.api.ComputerApi#getById(java.lang.Integer)
	 */
	@Override
	@GET
	@Path("{id}")
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
	public Response getAll() {
		LOGGER.debug("JSON getAll");
		final List<ComputerDTO> dtos = computerService.findAll(null);
		return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
				: Response.noContent().build();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getByName(java.lang.String)
	 */
	@Override
	@GET
	@Path("name/{name}")
	public Response getByName(@PathParam("name") final String name) {
		LOGGER.debug("JSON to computer {}", name);
		final List<ComputerDTO> dtos = computerService.find(name, null);
		return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
				: Response.noContent().build();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.api.ComputerApi#create(com.excilys.cdb.dto.ComputerDTO)
	 */
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ComputerDTO dto) {
		final ComputerDTO computer = computerService.add(mapper.toModel(dto));
		return computer.getId() != -1 ? Response.ok(computer).build()
				: Response.noContent().build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.api.ComputerApi#update(com.excilys.cdb.dto.ComputerDTO)
	 */
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ComputerDTO dto) {
		final ComputerDTO computer = computerService
				.update(mapper.toModel(dto));
		return computer.getId() != -1 ? Response.ok(computer).build()
				: Response.noContent().build();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#delete(java.lang.Integer)
	 */
	@Override
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer id) {
		computerService.delete(id);
		return computerService.find(id) == null ? Response.ok(true).build()
				: Response.status(Status.BAD_REQUEST).build();
	}

}
