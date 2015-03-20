package com.excilys.cdb.util;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.util.dto.ComputerDTO;

public class ComputerPage extends Page<Computer> {

	@Override
	protected ComputerDTO getDTO(Computer computer) {
		return ComputerDTO.toDTO(computer);
	}

	@Override
	protected ComputerService getService() {
		return ComputerServiceImpl.INSTANCE.getInstance();
	}

}
