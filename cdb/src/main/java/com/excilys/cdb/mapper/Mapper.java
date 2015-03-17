package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The Interface Mapper.
 *
 * @param <T> the generic type
 */
public interface Mapper<T> {

	/**
	 * Row map.
	 *
	 * @param result the result
	 * @return the t
	 */
	public T rowMap(ResultSet result) throws SQLException;
}
