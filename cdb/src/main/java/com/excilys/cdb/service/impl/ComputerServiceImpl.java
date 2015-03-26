package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.dto.ComputerDTO;


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
		dao = ComputerDaoImpl.getInstance();
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
	public ComputerDTO find(int id) {
		Computer c = dao.find(id);
		ComputerDTO dto = null;
		if (c != null)
			dto = ComputerDTO.toDTO(c);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ComputerService#find(String)
	 */
	@Override
	public ComputerDTO find(String name) {
		Computer c = dao.find(name);
		ComputerDTO dto = null;
		if (c != null)
			dto = ComputerDTO.toDTO(c);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ComputerService#findAll()
	 */
	@Override
	public List<ComputerDTO> findAll() {
		List<Computer> companies = dao.findAll();
		List<ComputerDTO> dtos = new ArrayList<>();
		for (Computer c : companies)
			dtos.add(ComputerDTO.toDTO(c));

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ComputerService#findAll(int, int)
	 */
	@Override
	public List<ComputerDTO> findAll(int start, int offset) {
		List<Computer> companies = dao.findAll(start, offset);
		List<ComputerDTO> dtos = new ArrayList<>();
		for (Computer c : companies)
			dtos.add(ComputerDTO.toDTO(c));

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
	public ComputerDTO add(Computer computer) {
		Computer c = dao.create(computer);
		ComputerDTO dto = null;
		if (c != null)
			dto = ComputerDTO.toDTO(c);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ComputerService#delete(int)
	 */
	public void delete(Computer computer) {
		dao.delete(computer.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.Service#delete(int)
	 */
	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.ComputerService#update(com.excilys.cdb.model.
	 * Computer )
	 */
	@Override
	public ComputerDTO update(Computer computer) {
		Computer c = dao.update(computer);
		ComputerDTO dto = null;
		if (c != null)
			dto = ComputerDTO.toDTO(c);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.ComputerService#count()
	 */
	@Override
	public int count() {
		return dao.count();
	}
}
