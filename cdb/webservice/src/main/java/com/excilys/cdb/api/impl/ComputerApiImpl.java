package com.excilys.cdb.api.impl;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.api.ComputerApi;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.service.ComputerService;

/**
 * The Class ComputerApiImpl.
 */
@RestController
@RequestMapping(value = "api/computer", produces = MediaType.APPLICATION_JSON)
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
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<ComputerDTO> getById(@PathVariable("id") Integer id) {
		LOGGER.debug("JSON to computer {}", id);
		final ComputerDTO dto = computerService.find(id);
		return dto != null ? new ResponseEntity<ComputerDTO>(dto,
				HttpStatus.CREATED) : new ResponseEntity<ComputerDTO>(
						HttpStatus.BAD_REQUEST);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.api.ComputerApi#getAll()
	 */
	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<List<ComputerDTO>> getAll() {
		LOGGER.debug("JSON getAll");
		final List<ComputerDTO> dtos = computerService.findAll(null);
		return dtos != null && !dtos.isEmpty() ? new ResponseEntity<List<ComputerDTO>>(
				dtos, HttpStatus.OK) : new ResponseEntity<List<ComputerDTO>>(
						HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#getByName(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<ComputerDTO>> getByName(
			@PathVariable("name") final String name) {
		LOGGER.debug("JSON to computer {}", name);
		final List<ComputerDTO> dtos = computerService.find(name, null);
		return dtos != null && !dtos.isEmpty() ? new ResponseEntity<List<ComputerDTO>>(
				dtos, HttpStatus.OK) : new ResponseEntity<List<ComputerDTO>>(
						HttpStatus.BAD_REQUEST);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.api.ComputerApi#create(com.excilys.cdb.dto.ComputerDTO)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<ComputerDTO> create(ComputerDTO dto) {
		final ComputerDTO computer = computerService.add(mapper.toModel(dto));
		return computer.getId() != -1 ? new ResponseEntity<ComputerDTO>(dto,
				HttpStatus.OK) : new ResponseEntity<ComputerDTO>(
						HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.api.ComputerApi#update(com.excilys.cdb.dto.ComputerDTO)
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<ComputerDTO> update(ComputerDTO dto) {
		final ComputerDTO computer = computerService
				.update(mapper.toModel(dto));
		return computer.getId() != -1 ? new ResponseEntity<ComputerDTO>(dto,
				HttpStatus.OK) : new ResponseEntity<ComputerDTO>(
						HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.api.ComputerApi#delete(java.lang.Integer)
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ComputerDTO> delete(@PathVariable("id") Integer id) {
		computerService.delete(id);
		return new ResponseEntity<ComputerDTO>(HttpStatus.OK);
		// : new ResponseEntity<ComputerDTO>(HttpStatus.BAD_REQUEST);
	}

}
