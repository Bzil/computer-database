package com.excilys.cdb.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.api.CompanyApi;
import com.excilys.cdb.api.dto.CompanyJson;
import com.excilys.cdb.service.CompanyService;

/**
 * The Class CompanyApiImpl.
 */
@RestController
@RequestMapping(value = "api/company", produces = "application/json")
public class CompanyApiImpl implements CompanyApi {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyApiImpl.class);

	/**
	 * The company service.
	 */
	@Autowired
	private CompanyService companyService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.CompanyApi#getById(java.lang.Integer)
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<CompanyJson> getById(@PathVariable("id") Integer id) {
		LOGGER.info("JSON to company {}", id);
		LOGGER.info("{}", companyService.find(id));
		final CompanyJson dto = CompanyJson.to(companyService.find(id));
		return dto != null ? new ResponseEntity<>(dto, HttpStatus.CREATED) : new ResponseEntity<>(
				HttpStatus.BAD_REQUEST);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.CompanyApi#getAll()
	 */
	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyJson>> getAll() {
		LOGGER.info("JSON getAll");
		final List<CompanyJson> dtos = companyService.findAll(null).stream().map(CompanyJson::to)
				.collect(Collectors.toList());
		return dtos != null && !dtos.isEmpty() ? new ResponseEntity<>(dtos, HttpStatus.OK) : new ResponseEntity<>(
				HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.CompanyApi#delete(java.lang.Integer)
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<CompanyJson> delete(@PathVariable("id") Integer id) {
		companyService.delete(id);
		return companyService.find(id) == null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(
				HttpStatus.BAD_REQUEST);
	}
}
