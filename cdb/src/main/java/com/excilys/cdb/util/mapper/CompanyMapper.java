package com.excilys.cdb.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;

/**
 * The Class CompanyMapper.
 */
public class CompanyMapper implements Mapper<Company> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.mapper.Mapper#rowMap(java.sql.ResultSet)
	 */
	@Override
	public Company rowMap(ResultSet result) throws SQLException {
		Company company = new Company();
		company.setId(result.getInt("id"));
		company.setName(result.getString("name"));
		return company;
	}

}
