package com.excilys.cdb.service.impl;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.ComputerService;

public enum ComputerServiceImpl implements ComputerService {
	INSTANCE;
	
	private ComputerDao dao;
	
	private ComputerServiceImpl(){
		dao = ComputerDaoImpl.INSTANCE.getInstance();
	}
	
	public ComputerService getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Computer find(int id) {
		return dao.find(id);
	}

	@Override
	public List<Computer> findAll() {
		return dao.findAll();
	}

	@Override
	public Computer add(Computer computer) {
		return dao.create(computer);
	}

	@Override
	public void delete(Computer computer) {
		dao.delete(computer);

	}

	@Override
	public Computer update(Computer computer) {
		return dao.update(computer);
	}

}
