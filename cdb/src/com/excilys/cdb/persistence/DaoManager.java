package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Class DaoManager.
 */
public enum DaoManager {
	INSTANCE;
	
	/** The url. */
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	
	/** The login. */
	private static final String LOGIN = "admincdb";
	
	/** The password. */
	private static final String PASSWORD = "qwerty1234";
	
	/** The connection. */
	private static Connection connection;

	/**
	 * Open.
	 *
	 * @return the connection
	 */
	public Connection open() {
		if(connection == null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * Close.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void close() throws SQLException {
		if(connection!=null && !connection.isClosed()){
			connection.close();
			connection = null;
		}

	}

}
