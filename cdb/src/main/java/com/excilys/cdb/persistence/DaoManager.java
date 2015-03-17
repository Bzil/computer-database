package com.excilys.cdb.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The Class DaoManager.
 */
public enum DaoManager implements AutoCloseable{
	INSTANCE;

	private final static String FILE_NAME = "src/main/resources/mysql.properties";

	/** The connection. */
	private Connection connection;

	private final Properties prop = new Properties();

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		connection = null;
		try (
				InputStream ips = new FileInputStream(FILE_NAME);
				){
			prop.load(ips);
			String url = prop.getProperty("url");
			connection = (Connection) DriverManager.getConnection(url, prop);
		} catch (IOException e) {
			System.err.println(e);
		} catch (SQLException e) {
			System.err.println(e); 
		}
		return connection;
	}

	/**
	 * Close connection.
	 *
	 */
	@Override
	public void close() throws Exception {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
