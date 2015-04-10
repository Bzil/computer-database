package com.excilys.cdb.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


// TODO: Auto-generated Javadoc
/**
 * The Interface Mapper.
 *
 * @param <T>
 *            the generic type
 */
public interface Mapper<T> extends RowMapper<T> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
