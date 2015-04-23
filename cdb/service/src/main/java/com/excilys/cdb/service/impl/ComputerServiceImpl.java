package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.sort.SortCriteria;

/**
 * The Enum ComputerServiceImpl.
 */
@Service
public class ComputerServiceImpl implements ComputerService {

	/** The dao. */
	@Autowired
	private ComputerDao dao;

	@Autowired
	private ComputerMapper mapper;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerServiceImpl.class);

	public ComputerServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#find(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public ComputerDTO find(final int id) {
		LOGGER.debug("Looking for computer " + id);
		ComputerDTO dto = null;
		if (id > 0) {
			final Computer c = dao.find(id);

			if (c != null) {
				dto = mapper.toDto(c);
			}
		}
		LOGGER.debug("computer {}", dto);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#find(String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ComputerDTO> find(final String name, final SortCriteria criteria) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Looking for computer " + name);
		}
		final List<Computer> computers = dao.find(name, criteria);
		final List<ComputerDTO> dtos = new ArrayList<>();
		for (final Computer c : computers) {
			dtos.add(mapper.toDto(c));
		}
		return dtos;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#findAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ComputerDTO> findAll(final SortCriteria criteria) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Looking for all computer");
		}
		final List<Computer> companies = dao.findAll(criteria);
		final List<ComputerDTO> dtos = new ArrayList<>();
		for (final Computer c : companies) {
			dtos.add(mapper.toDto(c));
		}

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#findAll(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ComputerDTO> findAll(final int start, final int offset,
			final SortCriteria criteria) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Looking for all computer between " + start + " - "
					+ offset);
		}
		final List<Computer> companies = dao.findAll(start, offset, criteria);
		final List<ComputerDTO> dtos = new ArrayList<>();
		for (final Computer c : companies) {
			dtos.add(mapper.toDto(c));
		}
		return dtos;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.service.ComputerService#add(com.excilys.cdb.model.Computer
	 * )
	 */
	@Override
	@Transactional
	public ComputerDTO add(final Computer computer) {
		ComputerDTO dto = null;
		if (computer != null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Create" + computer);
			}
			final Computer c = dao.create(computer);

			if (c != null) {
				dto = mapper.toDto(c);
			}
		}
		return dto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#delete(int)
	 */
	@Override
	@Transactional
	public void delete(final Computer computer) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Delete " + computer);
		}
		dao.delete(computer.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.Service#delete(int)
	 */
	@Override
	@Transactional
	public void delete(final int id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Delete computer with id " + id);
		}
		dao.delete(id);
	}

	@Override
	@Transactional
	public void deleteByCompanyId(final int companyId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Delete computer with id " + companyId);
		}
		dao.deleteByCompanyId(companyId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.excilys.cdb.service.ComputerService#update(com.excilys.cdb.model.
	 * Computer )
	 */
	@Override
	public ComputerDTO update(final Computer computer) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Update " + computer);
		}
		final Computer c = dao.update(computer);
		ComputerDTO dto = null;
		if (c != null) {
			dto = mapper.toDto(c);
		}
		return dto;
	}

	@Override
	@Transactional
	public ComputerDTO saveOrUpdate(final Computer computer) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Save or Update " + computer);
		}
		return (computer.getId() > 0) ? update(computer) : add(computer);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#count()
	 */
	@Override
	@Transactional
	public long count() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Count");
		}
		return dao.count();
	}
}
