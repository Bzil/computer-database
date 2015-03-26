package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.util.dto.CompanyDTO;

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
		dao = CompanyDaoImpl.getInstance();
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
	public CompanyDTO find(int id) {
		Company c = dao.find(id);
		System.out.println(c);
		CompanyDTO dto = null;
		if (c != null)
			dto = CompanyDTO.toDTO(c);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#findAll()
	 */
	@Override
	public List<CompanyDTO> findAll() {
		List<Company> companies = dao.findAll();
		List<CompanyDTO> dtos = new ArrayList<>();
		for (Company c : companies)
			dtos.add(CompanyDTO.toDTO(c));

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#findAll(int, int)
	 */
	@Override
	public List<CompanyDTO> findAll(int start, int offset) {
		List<Company> companies = dao.findAll(start, offset);
		List<CompanyDTO> dtos = new ArrayList<>();
		for (Company c : companies)
			dtos.add(CompanyDTO.toDTO(c));

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.CompanyService#add(com.excilys.cdb.model.Company)
	 */
	@Override
	public CompanyDTO add(Company company) {
		Company c = dao.create(company);
		CompanyDTO dto = null;
		if (c != null)
			dto = CompanyDTO.toDTO(c);
		return dto;
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
		dao.delete(company.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.Service#delete(int)
	 */
	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.service.CompanyService#update(com.excilys.cdb.model.Company
	 * )
	 */
	@Override
	public CompanyDTO update(Company company) {
		Company c = dao.update(company);
		CompanyDTO dto = null;
		if (c != null)
			dto = CompanyDTO.toDTO(c);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#count()
	 */
	@Override
	public int count() {
		return dao.count();
	}
}
