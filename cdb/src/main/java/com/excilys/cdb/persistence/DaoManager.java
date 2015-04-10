package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class DaoManager.
 */
@Component
public class DaoManager {

	@Autowired
	private BasicDataSource dataSource;

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public void closeConnection() {
		try {
			dataSource.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
