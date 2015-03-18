package com.excilys.cdb.service.impl;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.ComputerService;

/**
 * The Enum ComputerServiceImpl.
 */
public enum ComputerServiceImpl implements ComputerService {

	/** The instance. */
	INSTANCE;

	/** The dao. */
	private ComputerDao dao;

	/**
	 * Instantiates a new computer service impl.
	 */
	private ComputerServiceImpl() {
		dao = ComputerDaoImpl.INSTANCE.getInstance();
	}

	/**
	 * Gets the single instance of ComputerServiceImpl.
	 *
	 * @return single instance of ComputerServiceImpl
	 */
	public ComputerService getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ComputerService#find(int)
	 */
	@Override
	public Computer find(int id) {
		return dao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ComputerService#findAll()
	 */
	@Override
	public List<Computer> findAll() {
		return dao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.ComputerService#add(com.excilys.cdb.model.Computer
	 * )
	 */
	@Override
	public Computer add(Computer computer) {
		return dao.create(computer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.ComputerService#delete(com.excilys.cdb.model.
	 * Computer)
	 */
	@Override
	public void delete(Computer computer) {
		dao.delete(computer);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.ComputerService#update(com.excilys.cdb.model.
	 * Computer)
	 */
	@Override
	public Computer update(Computer computer) {
		return dao.update(computer);
	}

	@Override
	public List<Computer> findAll(int start, int offset) {
		return dao.findAll(start, offset);
	}

}
