package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.DaoManager;
import com.excilys.cdb.util.mapper.CompanyMapper;
import com.excilys.cdb.util.mapper.Mapper;

/**
 * The Class CompanyDaoImpl.
 */
public enum CompanyDaoImpl implements CompanyDao {
	INSTANCE;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	private CompanyDaoImpl() {
	}

	public static CompanyDao getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#find(int)
	 */
	@Override
	public Company find(int id) {
		LOGGER.trace("Find company with id " + id);
		Company company = null;
		Mapper<Company> mapper = new CompanyMapper();
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM company WHERE id = ?");) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				company = (Company) mapper.rowMap(result);
			}
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't execute select request with id " + id);
			LOGGER.debug(e.toString());
		}
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.CompanyDao#create(com.excilys.cdb.model.Compagny
	 * )
	 */
	@Override
	public Company create(Company company) {
		LOGGER.trace("Create company " + company);
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO company(name) values (?)",
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, company.getName());
			statement.executeUpdate();
			// get keys
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				company.setId(generatedKeys.getInt(1));
			}
			generatedKeys.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't exectute create request of " + company);
			e.printStackTrace();
		}
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.CompanyDao#update(com.excilys.cdb.model.Compagny
	 * )
	 */
	@Override
	public Company update(Company company) {
		LOGGER.trace("Update company " + company);
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE company SET name = ? WHERE id = ?");) {
			statement.setString(1, company.getName());
			statement.setInt(2, company.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			company = null;
			LOGGER.debug("Can't exceute update request of " + company);
			e.printStackTrace();
		}
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.persistence.CompanyDao#delete(com.excilys.cdb.model.Compagny
	 * )
	 */
	@Override
	public void delete(Company company) {
		LOGGER.trace("Delete company " + company);
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM company WHERE id = ?");) {
			statement.setInt(1, company.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.debug("Can't execute delete request of " + company);
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll()
	 */
	@Override
	public List<Company> findAll() {
		List<Company> companies = new ArrayList<>();
		Mapper<Company> mapper = new CompanyMapper();
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM company");) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				companies.add((Company) mapper.rowMap(result));
			}
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't find all computer");
			e.printStackTrace();
		}
		return companies;
	}

	@Override
	public List<Company> findAll(int start, int offset) {
		List<Company> companies = new ArrayList<>();
		Mapper<Company> mapper = new CompanyMapper();
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM company LIMIT ?, ? ");) {
			statement.setInt(1, start);
			statement.setInt(2, offset);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				companies.add((Company) mapper.rowMap(result));
			}
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't find all computer between [" + start + "-"
					+ (start + offset) + "]");
			e.printStackTrace();
		}
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#count()
	 */
	@Override
	public int count() {
		int count = -1;
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT COUNT(*) AS count FROM company");) {
			ResultSet result = statement.executeQuery();
			result.next();
			count = result.getInt("count");
			result.close();
		} catch (SQLException e) {
			LOGGER.debug("Can't execute count request");
			e.printStackTrace();
		}
		return count;

	}

}
