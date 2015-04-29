package com.excilys.cdb.api.impl;

import com.excilys.cdb.api.ComputerApi;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.net.URI;
import java.util.List;

/**
 * The Class ComputerApiImpl.
 */
@RestController
@RequestMapping(value = "api/computer", produces = "application/json")
public class ComputerApiImpl implements ComputerApi {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerApiImpl.class);

    /**
     * The computer service.
     */
    @Autowired
    private ComputerService computerService;

    @Autowired
    private ServletContext context;

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
        return dto != null ? new ResponseEntity<>(dto,
                HttpStatus.CREATED) : new ResponseEntity<>(
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
        return dtos != null && !dtos.isEmpty() ? new ResponseEntity<>(
                dtos, HttpStatus.OK) : new ResponseEntity<>(
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
        return dtos != null && !dtos.isEmpty() ? new ResponseEntity<>(
                dtos, HttpStatus.OK) : new ResponseEntity<>(
                HttpStatus.BAD_REQUEST);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.excilys.cdb.api.ComputerApi#create(com.excilys.cdb.dto.ComputerDTO)
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ComputerDTO> create(@RequestBody ComputerDTO dto) {
        LOGGER.debug("Create computer {}", dto);
        final ComputerDTO computer = computerService.add(mapper.toModel(dto));
        final URI uri = URI.create(context.getContextPath() + "/api/computer/"
                + computer.getId());
        return computer.getId() != -1 ? ResponseEntity.created(uri).body(
                computer) : new ResponseEntity<>(
                HttpStatus.NO_CONTENT);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.excilys.cdb.api.ComputerApi#update(com.excilys.cdb.dto.ComputerDTO)
     */
    @Override
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<ComputerDTO> update(@RequestBody ComputerDTO dto) {
        LOGGER.debug("Update computer {}", dto);
        final ComputerDTO computer = computerService
                .update(mapper.toModel(dto));
        return computer.getId() != -1 ? new ResponseEntity<>(dto,
                HttpStatus.OK) : new ResponseEntity<>(
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
        LOGGER.debug("Delete computer {}", id);
        computerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
