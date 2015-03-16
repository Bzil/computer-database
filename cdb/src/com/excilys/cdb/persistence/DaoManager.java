package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Class DaoManager.
 */
public class DaoManager {

	/** The url. */
	private static String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	
	/** The login. */
	private static String login = "admincdb";
	
	/** The password. */
	private static String password = "qwerty1234";
	
	/** The connection. */
	private static Connection connection;

	/**
	 * Open.
	 *
	 * @return the connection
	 */
	public static Connection open() {
		if(connection==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, login, password);
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
	public static void close() throws SQLException {
		if(connection!=null && !connection.isClosed()){
			connection.close();
			connection = null;
		}

	}
}
