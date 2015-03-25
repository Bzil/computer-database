package com.excilys.cdb.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * The Class ComputerMapper.
 */
public class ComputerMapper implements Mapper<Computer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.mapper.Mapper#rowMap(java.sql.ResultSet)
	 */
	@Override
	public Computer rowMap(ResultSet result) throws SQLException {
		Computer computer = new Computer();
		computer.setId(result.getInt("id"));
		computer.setName(result.getString("name"));
		if (result.getTimestamp("introduced") != null) {
			computer.setIntroduced(result.getTimestamp("introduced")
					.toLocalDateTime());
		}
		if (result.getTimestamp("discontinued") != null) {
			computer.setDiscontinued(result.getTimestamp("discontinued")
					.toLocalDateTime());
		}
		computer.setCompany(new Company());
		computer.getCompany().setId(result.getInt("company_id"));

		return computer;
	}

}
