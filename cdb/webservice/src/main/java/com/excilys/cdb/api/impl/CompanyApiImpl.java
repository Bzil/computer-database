package com.excilys.cdb.api.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.api.CompanyApi;
import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.service.CompanyService;

/**
 * The Class CompanyApiImpl.
 */
@Path("company")
public class CompanyApiImpl implements CompanyApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyApiImpl.class);

	/** The company service. */
	@Autowired
	private CompanyService companyService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.CompanyApi#getById(java.lang.Integer)
	 */
	@Override
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		LOGGER.debug("JSON getAll");
		final List<CompanyDTO> dtos = companyService.findAll(null);
		return dtos != null && !dtos.isEmpty() ? Response.ok(dtos).build()
				: Response.noContent().build();
	}
}
