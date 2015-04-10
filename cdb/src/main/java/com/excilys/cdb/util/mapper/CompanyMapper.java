package com.excilys.cdb.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyMapper.
 */
@Component
public class CompanyMapper implements Mapper<Company> {

	/* (non-Javadoc)
	 * @see com.excilys.cdb.util.mapper.Mapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		return company;
	}


}
