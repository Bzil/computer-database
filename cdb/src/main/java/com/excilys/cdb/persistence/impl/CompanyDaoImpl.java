package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.mapper.Mapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.DaoManager;

/**
 * The Class CompanyDaoImpl.
 */
public enum CompanyDaoImpl implements CompanyDao {
	INSTANCE;

	private CompanyDaoImpl() {
	}

	public CompanyDao getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.persistence.CompanyDao#find(int)
	 */
	@Override
	public Company find(int id) {
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

		} catch (SQLException e) {
			e.printStackTrace();
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
		Company ojb = null;
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO company(name) values (?)",
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, company.getName());
			statement.executeUpdate();
			// get keys
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				ojb = this.find(generatedKeys.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ojb;
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
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE company SET name = ? WHERE id = ?");) {
			statement.setString(1, company.getName());
			statement.setInt(2, company.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.find(company.getId());
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
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM company WHERE id = ?");) {
			statement.setInt(1, company.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
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
		List<Company> companys = new ArrayList<>();
		Mapper<Company> mapper = new CompanyMapper();
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM company");) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				companys.add((Company) mapper.rowMap(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companys;
	}

	@Override
	public List<Company> findAll(int start, int offset) {
		List<Company> companys = new ArrayList<>();
		Mapper<Company> mapper = new CompanyMapper();
		try (Connection connection = DaoManager.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM computer LIMIT ?, ? ");) {
			statement.setInt(1, start);
			statement.setInt(2, offset);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				companys.add((Company) mapper.rowMap(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companys;
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
			ResultSet res = statement.executeQuery();
			res.next();
			count = res.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

	}

}
