package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

	@Override
	public Computer rowMap(ResultSet result) throws SQLException {
		Computer computer = new Computer();
		computer.setId( result.getInt("id"));
		computer.setName(result.getString("name"));
		if(result.getTimestamp("introduced") != null ){
			computer.setIntroduced(result.getTimestamp("introduced").toLocalDateTime());
		}
		if(result.getTimestamp("discontinued") != null ) {
			computer.setDiscontinued(result.getTimestamp("discontinued").toLocalDateTime());
		}
		computer.setCompanyId(result.getInt("company_id"));

		return computer;
	}

}
