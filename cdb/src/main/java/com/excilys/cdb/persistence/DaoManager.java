package com.excilys.cdb.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

	/** The Constant LOOGER. */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(DaoManager.class);

	/** The connection thread local. */
	private static ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			Connection connection = null;
			try {
				connection = pool.getConnection();
			} catch (SQLException e) {
				LOGGER.error(e.getStackTrace().toString());
			}
			return connection;
		}
	};

	/** The properties file. */
	String propertiesFile = "/config.properties";

	/** The driver. */
	String driver = "com.mysql.jdbc.Driver";

	/** The pool. */
	private static BoneCP pool = null;

	private boolean isTransactional = false;

	/**
	 * Instantiates a new dao manager.
	 */
	private void init() {
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream(propertiesFile));
			Class.forName(prop.getProperty("driver"));
		} catch (IOException e) {
			LOGGER.error("Can't open/read mysql.properties");
			LOGGER.error(e.getStackTrace().toString());
		} catch (RuntimeException e) {
			LOGGER.error("Runtime : " + this.getClass());
			LOGGER.error(e.getStackTrace().toString());
		} catch (ClassNotFoundException e) {
			LOGGER.error("Class not found");
			LOGGER.error(e.getStackTrace().toString());
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
			LOGGER.error("Could not connect to database");
			LOGGER.error(e.getStackTrace().toString());
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		if (pool == null) {
			init();
		}
		Connection connection = CONNECTION_THREAD_LOCAL.get();
		if (connection == null) {
			CONNECTION_THREAD_LOCAL.set(pool.getConnection());
		} else {
			isTransactional = true;
			connection.setAutoCommit(false);
		}

		return connection;
	}

	/**
	 * Close connection.
	 */
	public void closeConnection() {
		Connection connect = CONNECTION_THREAD_LOCAL.get();
		if (!isTransactional && connect != null) {
			try {
				connect.setAutoCommit(true);
				connect.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failed");
				LOGGER.error(e.getStackTrace().toString());
			}
			CONNECTION_THREAD_LOCAL.remove();
			LOGGER.info("close connection succeed");
		}
	}

	/**
	 * Start transaction.
	 */
	public void startTransaction() {
		final Connection connection = CONNECTION_THREAD_LOCAL.get();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
				LOGGER.info("start transaction succeed");
			} catch (SQLException e) {
				LOGGER.error("start transaction failed");
				LOGGER.error(e.getStackTrace().toString());
			}
		}
	}

	/**
	 * Commit.
	 */
	public void commit() {
		final Connection connection = CONNECTION_THREAD_LOCAL.get();
		if (connection != null) {
			try {
				connection.commit();
				LOGGER.info("commit succeed");
				isTransactional = false;
			} catch (SQLException e) {
				LOGGER.error("commit failed");
				LOGGER.error(e.getStackTrace().toString());
			} finally {
				closeConnection();
			}
		}
	}

	/**
	 * Rollback.
	 */
	public void rollback() {
		final Connection connection = CONNECTION_THREAD_LOCAL.get();
		if (connection != null) {
			try {
				connection.rollback();
				LOGGER.info("rollback succeed");
			} catch (SQLException e) {
				LOGGER.error("rollback failed");
				LOGGER.error(e.getStackTrace().toString());
			}
		}

	}
}
