package com.excilys.cdb.manage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

import com.excilys.cdb.persistence.DaoManager;

public class H2dbManager {

	private static final String PROPERTIES_FILE = "config.properties";
	private static final String SCHEMA = "src/test/resources/schema.sql";
	public static IDatabaseTester dbTester;
	public static String jdbcDriver;
	public static String jdbcUrl;
	public static String user;
	public static String password;

	static {
		final Properties properties = new Properties();
		try (final InputStream is = DaoManager.class.getClassLoader()
				.getResourceAsStream(PROPERTIES_FILE)) {
			properties.load(is);
			jdbcDriver = properties.getProperty("jdbc.test.driver");
			jdbcUrl = properties.getProperty("jdbc.test.url");
			user = properties.getProperty("jdbc.test.username");
			password = properties.getProperty("jdbc.test.password");
		} catch (final IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void cleanlyInsert(final IDataSet dataSet) throws Exception {
		dbTester = new JdbcDatabaseTester(jdbcDriver, jdbcUrl, user, password);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		dbTester.setDataSet(dataSet);
		dbTester.onSetup();
	}

	/**
	 * Execute the sql file.
	 *
	 * @param file
	 *            File to execute
	 * @param connection
	 *            Connection
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void executeSqlFile(final String file,
			final Connection connection) throws IOException, SQLException {
		final InputStream is = new FileInputStream(file);
		final BufferedReader br = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		final StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str + "\n ");
		}
		try (final Statement stmt = connection.createStatement()) {
			stmt.execute(sb.toString());
		}
		br.close();
	}

	public static void executeSqlFile(final Connection connection)
			throws IOException, SQLException {
		executeSqlFile(SCHEMA, connection);
	}

	public static Connection getConnection() throws IOException, SQLException {
		final Properties properties = new Properties();
		try (final InputStream is = DaoManager.class.getClassLoader()
				.getResourceAsStream(PROPERTIES_FILE)) {
			properties.load(is);
			final String url = properties.getProperty("jdbc.test.url");
			return DriverManager.getConnection(url, properties);
		}
	}
}
