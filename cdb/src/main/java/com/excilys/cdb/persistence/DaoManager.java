package com.excilys.cdb.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DaoManager.
 */
public enum DaoManager implements AutoCloseable {
	INSTANCE;
	
	
	String propertiesFile = "mysql.properties";

	/** The Constant LOOGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoManager.class);
	
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
				
			InputStream ips = DaoManager.class.getClassLoader().getResourceAsStream(propertiesFile)) {
			prop.load(ips);
			String url = prop.getProperty("url");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = (Connection) DriverManager.getConnection(url, prop);
			LOGGER.info("Connect to Database with this url : " + url);
		} catch (IOException e) {
			LOGGER.debug("Can't open/read mysql.properties");
			System.err.println(e);
		} catch (SQLException e) {
			LOGGER.debug("Can't open new connection");
			System.err.println(e);
		} catch (Exception e) {
			LOGGER.debug("Can't open new connection");
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
			LOGGER.debug("Can't close existing connection");
			System.err.println(e);
		}
	}
}
