package com.excilys.cdb.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * The Class DaoManager.
 */
public enum DaoManager {

	/** The instance. */
	INSTANCE;

	/** The properties file. */
	String propertiesFile = "/config.properties";
	
	String driver = "com.mysql.jdbc.Driver";

	/** The Constant LOOGER. */
	private final Logger LOGGER = LoggerFactory
			.getLogger(DaoManager.class);

	/** The pool. */
	private BoneCP pool = null;

	/**
	 * Instantiates a new dao manager.
	 */
	private DaoManager() {
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream(propertiesFile));
			Class.forName(prop.getProperty("driver"));
		} catch (IOException e) {
			LOGGER.error("Can't open/read mysql.properties");
			System.err.println(e);
		} catch (RuntimeException e) {
			LOGGER.error("Runtime : "+ this.getClass());
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Class not found");
			System.err.println(e);
		}

		BoneCPConfig config = new BoneCPConfig();
		config.setUsername(prop.getProperty("user"));
		config.setPassword(prop.getProperty("password"));
		config.setJdbcUrl(prop.getProperty("url"));
		config.setMinConnectionsPerPartition(5);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(1);
		try {
			pool = new BoneCP(config);
		} catch (SQLException e) {
			String error = "Could not connect to database";
			LOGGER.error(error);
			e.printStackTrace();
		}
	}


	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		try {
			return pool.getConnection();
		} catch (SQLException e) {
			LOGGER.warn("Can't get connection");
		}
		return null;
	}

	/**
	 * Close connection.
	 *
	 * @param statement the statement
	 * @param connection the connection
	 */
	public void close(Statement statement, Connection connection) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			LOGGER.warn("Can't close statement");
		}
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			LOGGER.warn("Can't close connection");
		}
	}

}
