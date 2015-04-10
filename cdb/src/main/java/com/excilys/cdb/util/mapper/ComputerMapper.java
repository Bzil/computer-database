package com.excilys.cdb.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * The Class ComputerMapper.
 */
@Component
public class ComputerMapper implements Mapper<Computer> {

	private static final String COMPA_NAME = "compa.name";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String INTRODUCED = "introduced";
	private static final String DISCONTINUED = "discontinued";
	private static final String COMPANY_ID = "company_id";

	/* (non-Javadoc)
	 * @see com.excilys.cdb.util.mapper.Mapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getInt(ID));
		computer.setName(rs.getString(NAME));
		if (rs.getTimestamp(INTRODUCED) != null) {
			computer.setIntroduced(rs.getTimestamp(INTRODUCED)
					.toLocalDateTime());
		}
		if (rs.getTimestamp(DISCONTINUED) != null) {
			computer.setDiscontinued(rs.getTimestamp(DISCONTINUED)
					.toLocalDateTime());
		}
		int companyId = rs.getInt(COMPANY_ID);
		if (companyId > 0) {
			String companyName = rs.getString(COMPA_NAME);
			computer.setCompany(new Company(companyId, companyName));
		}
		return computer;
	}
}
