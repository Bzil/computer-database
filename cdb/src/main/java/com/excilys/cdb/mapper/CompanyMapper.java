package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;

public class CompanyMapper implements Mapper<Company> {

	@Override
	public Company rowMap(ResultSet result) throws SQLException {
		Company company = new Company();
		company.setId( result.getInt("id"));
		company.setName(result.getString("name"));
		return company;
	}

}
