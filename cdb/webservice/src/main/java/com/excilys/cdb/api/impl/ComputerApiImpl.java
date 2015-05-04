/**
 *
 * @author Basile
 */
package com.excilys.cdb.api.impl;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.api.ComputerApi;
import com.excilys.cdb.api.dto.ComputerJson;
import com.excilys.cdb.service.ComputerService;

/**
 * The Class ComputerApiImpl.
 */
@RestController
@RequestMapping(value = "api/computer", produces = "application/json")
public class ComputerApiImpl implements ComputerApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerApiImpl.class);

	/** The computer service. */
	@Autowired
	private ComputerService computerService;

	/** The context. */
	@Autowired
	private ServletContext context;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getById(java.lang.Integer)
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<ComputerJson> getById(@PathVariable("id") Integer id) {
		LOGGER.info("JSON to computer {}, computer : {}", id, computerService.find(id));
		final ComputerJson dto = ComputerJson.to(computerService.find(id));
		return dto != null ? new ResponseEntity<>(dto, HttpStatus.CREATED) : new ResponseEntity<>(
				HttpStatus.BAD_REQUEST);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getAll()
	 */
	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<List<ComputerJson>> getAll() {
		LOGGER.info("JSON getAll");
		final List<ComputerJson> dtos = computerService.findAll(null).stream().map(ComputerJson::to)
				.collect(Collectors.toList());
		return dtos != null && !dtos.isEmpty() ? new ResponseEntity<>(dtos, HttpStatus.OK) : new ResponseEntity<>(
				HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getByName(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<ComputerJson>> getByName(@PathVariable("name") final String name) {
		LOGGER.info("JSON to computer {}", name);
		final List<ComputerJson> dtos = computerService.find(name, null).stream().map(ComputerJson::to)
				.collect(Collectors.toList());
		return dtos != null && !dtos.isEmpty() ? new ResponseEntity<>(dtos, HttpStatus.OK) : new ResponseEntity<>(
				HttpStatus.BAD_REQUEST);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.api.ComputerApi#create(com.excilys.cdb.dto.ComputerJson)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ComputerJson> create(@RequestBody ComputerJson dto) {
		LOGGER.info("Create computer {}", dto);
		final ComputerJson computer = ComputerJson.to(computerService.add(ComputerJson.from(dto)));
		final URI uri = URI.create(context.getContextPath() + "/api/computer/" + computer.getId());
		return computer.getId() != -1 ? ResponseEntity.created(uri).body(computer) : new ResponseEntity<>(
				HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.api.ComputerApi#update(com.excilys.cdb.dto.ComputerJson)
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ComputerJson> update(@RequestBody ComputerJson dto) {
		LOGGER.info("Update computer {}", dto);
		final ComputerJson computer = ComputerJson.to(computerService.add(ComputerJson.from(dto)));
		return computer.getId() != -1 ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(
				HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#delete(java.lang.Integer)
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ComputerJson> delete(@PathVariable("id") Integer id) {
		LOGGER.info("Delete computer {}", id);
		computerService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
