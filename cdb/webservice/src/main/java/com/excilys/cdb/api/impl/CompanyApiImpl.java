package com.excilys.cdb.api.impl;

import java.util.List;

import javax.ws.rs.DELETE;
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

import com.excilys.cdb.api.CompanyApi;
import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.service.CompanyService;

/**
 * The Class CompanyApiImpl.
 */
@Path("company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyApiImpl implements CompanyApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyApiImpl.class);

	/** The company service. */
	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.api.CompanyApi#getById(java.lang.Integer)
	 */
	@Override
	@GET
	@Path("{id}")
	public Response getById(@PathParam("id") Integer id) {
		LOGGER.debug("JSON to company {}", id);
		final CompanyDTO dto = companyService.find(id);
		return dto != null ? Response.ok(dto).build() : Response.noContent()
				.build();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.CompanyApi#getAll()
	 */
	@Override
	@GET
	@Path("list")
	public Response getAll() {
		LOGGER.debug("JSON getAll");
		final List<CompanyDTO> dtos = companyService.findAll(null);
		return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
				: Response.noContent().build();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.CompanyApi#delete(java.lang.Integer)
	 */
	@Override
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer id) {
		companyService.delete(id);
		return companyService.find(id) == null ? Response.ok(true).build()
				: Response.status(Status.BAD_REQUEST).build();
	}
}
