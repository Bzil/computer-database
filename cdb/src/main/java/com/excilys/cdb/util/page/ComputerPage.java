package com.excilys.cdb.util.page;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.dto.ComputerDTO;

public class ComputerPage extends Page<Computer, ComputerDTO> {

	@Override
	protected ComputerDTO getDTO(Computer computer) {
		return ComputerDTO.toDTO(computer);
	}
}
