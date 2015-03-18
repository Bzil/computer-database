package com.excilys.cdb.service.impl;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.service.CompanyService;

/**
 * The Enum CompanyServiceImpl.
 */
public enum CompanyServiceImpl implements CompanyService {

	/** The instance. */
	INSTANCE;

	/** The dao. */
	private CompanyDao dao;

	/**
	 * Instantiates a new company service impl.
	 */
	private CompanyServiceImpl() {
		dao = CompanyDaoImpl.INSTANCE.getInstance();
	}

	/**
	 * Gets the single instance of CompanyServiceImpl.
	 *
	 * @return single instance of CompanyServiceImpl
	 */
	public CompanyService getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#find(int)
	 */
	@Override
	public Company find(int id) {
		return dao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#findAll()
	 */
	@Override
	public List<Company> findAll() {
		return dao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.CompanyService#add(com.excilys.cdb.model.Company)
	 */
	@Override
	public Company add(Company company) {
		return dao.create(company);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.CompanyService#delete(com.excilys.cdb.model.Company
	 * )
	 */
	@Override
	public void delete(Company company) {
		dao.delete(company);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.CompanyService#update(com.excilys.cdb.model.Company
	 * )
	 */
	@Override
	public Company update(Company company) {
		return dao.update(company);
	}

	@Override
	public List<Company> findAll(int start, int offset) {
		return dao.findAll(start, offset);
	}

}
