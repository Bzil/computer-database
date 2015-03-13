package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.DaoManager;

/**
 * The Class CompanyDaoImpl.
 */
public enum CompanyDaoImpl implements CompanyDao {
	INSTENSE;

	/** The connection. */
	private Connection connection;

	private CompanyDaoImpl(){
		connection = DaoManager.open();
	}

	public CompanyDao getInstance(){
		return INSTENSE;
	}

	public Connection getConnection()
	{
		return connection;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#find(int)
	 */
	@Override
	public Company find(int id) {
		String query = "SELECT * FROM company WHERE id = " + id;
		Company company = null;
		try {
			ResultSet result = connection.createStatement().executeQuery(query);
			if(result.first())
				company = new Company(id,result.getString("name"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#create(com.excilys.cdb.model.Compagny)
	 */
	@Override
	public Company create(Company company) {
		String query = "INSERT INTO company(name) values (?)";
		Company ojb = null;
		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
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

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#update(com.excilys.cdb.model.Compagny)
	 */
	@Override
	public Company update(Company company) {
		String query = "UPDATE company SET name = ? WHERE id = ?";
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, company.getName());
			statement.setInt(2, company.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.find(company.getId());
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#delete(com.excilys.cdb.model.Compagny)
	 */
	@Override
	public void delete(Company company) {
		String query ="DELETE FROM company WHERE id = " + company.getId();
		try {
			this.connection.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll()
	 */
	@Override
	public List<Company> findAll() {
		String query = "SELECT * FROM company";
		List<Company> companys = new ArrayList<>();
		try {
			ResultSet result = connection.createStatement().executeQuery(query);
			while (result.next()) {
				companys.add(new Company(result.getInt("id"), result.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companys;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#count()
	 */
	@Override
	public int count() {
		String query = "SELECT COUNT(*) AS count FROM company";
		PreparedStatement counter;
		int count = -1;
		try {
			counter = this.connection.prepareStatement(query);
			ResultSet res = counter.executeQuery();
			res.next();
			count = res.getInt("count");
		} catch(SQLException e){ 
			e.printStackTrace(); 
		}
		return count;

	}

}
