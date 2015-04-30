package com.excilys.cdb.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
	/**
	 * The dao.
	 */
	@Autowired
	private ComputerDao dao;
	@Autowired
	private ComputerMapper mapper;

	public ComputerServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#find(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Computer find(final int id) {
		LOGGER.info("Looking for computer {}", id);
		Computer c = null;
		if (id > 0) {
			c = dao.find(id);
		}
		LOGGER.info("computer {}", c);
		return c;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#find(String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Computer> find(final String name, final SortCriteria criteria) {
		LOGGER.info("Looking for computer {}", name);
		return dao.find(name, criteria);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#find(String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Computer> find(String name, int start, int offset, SortCriteria criteria) {
		LOGGER.info("Looking for computer {}", name);
		return dao.find(name, criteria);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#findAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Computer> findAll(final SortCriteria criteria) {
		LOGGER.info("Looking for all computer");
		return dao.findAll(criteria);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#findAll(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Computer> findAll(final int start, final int offset, final SortCriteria criteria) {
		LOGGER.info("Looking for all computer between {} - {}", start, offset);
		return dao.findAll(start, offset, criteria);

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
	public Computer add(final Computer computer) {
		Computer c = null;
		if (computer != null) {
			LOGGER.info("Create" + computer);
			c = dao.create(computer);
		}
		return c;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.ComputerService#delete(int)
	 */
	@Override
	@Transactional
	public void delete(final Computer computer) {
		LOGGER.info("Delete {}", computer);
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
		LOGGER.info("Delete computer with id {}", id);
		dao.delete(id);
	}

	@Override
	@Transactional
	public void deleteByCompanyId(final int companyId) {
		LOGGER.info("Delete computer with id {}", companyId);
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
	@Transactional
	public Computer update(final Computer computer) {
		LOGGER.info("Update {}", computer);
		return dao.update(computer);
	}

	@Override
	@Transactional
	public Computer saveOrUpdate(final Computer computer) {
		LOGGER.info("Save or Update {}", computer);
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
		LOGGER.info("Count");
		return dao.count();
	}
}
