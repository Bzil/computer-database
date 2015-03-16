package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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

	private final Map<String, PreparedStatement> queries = new HashMap<>();

	private CompanyDaoImpl(){
		connection = DaoManager.open();
		prepareQueries();
	}

	public CompanyDao getInstance(){
		return INSTENSE;
	}

	public Connection getConnection()
	{
		return connection;
	}

	private void prepareQueries() {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM company WHERE id = ?");
			queries.put("find", statement);
			statement =  connection.prepareStatement("INSERT INTO company(name) values (?)",Statement.RETURN_GENERATED_KEYS);
			queries.put("create", statement);
			statement = connection.prepareStatement("UPDATE company SET name = ? WHERE id = ?");
			queries.put("update", statement);
			statement = connection.prepareStatement("DELETE FROM company WHERE id = ?");
			queries.put("delete", statement);
			statement = connection.prepareStatement("SELECT * FROM company");
			queries.put("findAll", statement);
			statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM company");
			queries.put("count", statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#find(int)
	 */
	@Override
	public Company find(int id) {
		Company company = null;
		try {
			PreparedStatement statement = queries.get("find");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
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
		Company ojb = null;
		try {
			PreparedStatement statement = queries.get("create");
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
		try {
			PreparedStatement statement = queries.get("update");
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
		try {
			PreparedStatement statement = queries.get("delete");
			statement.setInt(1, company.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.persistence.CompanyDao#findAll()
	 */
	@Override
	public List<Company> findAll() {
		List<Company> companys = new ArrayList<>();
		try {
			ResultSet result = queries.get("findAll").executeQuery();
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
		int count = -1;
		try {
			ResultSet res = queries.get("count").executeQuery();
			res.next();
			count = res.getInt("count");
		} catch(SQLException e){ 
			e.printStackTrace(); 
		}
		return count;

	}

}
