package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface ComputerService {
	
	public Computer find(int id);
	
	public List<Computer> findAll();
	
	public Computer add(Computer computer);
	
	public void delete(Computer computer);
	
	public Computer update(Computer computer);
}
