package com.excilys.cdb.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;

/**
 * The Class CompanyMapper.
 */
@Component
public class CompanyMapper implements Mapper<Company> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.util.mapper.Mapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Company mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		final Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		return company;
	}

}
