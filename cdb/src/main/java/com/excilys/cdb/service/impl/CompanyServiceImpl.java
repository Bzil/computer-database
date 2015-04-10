package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.DaoManager;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.sort.SortCriteria;

/**
 * The Enum CompanyServiceImpl.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	/** The dao. */
	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private ComputerDao computerDao;

	@Autowired
	private DaoManager manager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#find(int)
	 */
	@Override
	public CompanyDTO find(int id) {
		CompanyDTO dto = null;
		if (id > 0) {
			Company c = companyDao.find(id);

			if (c != null) {
				dto = CompanyDTO.toDTO(c);
			}
		}
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#findAll()
	 */
	@Override
	public List<CompanyDTO> findAll(SortCriteria criteria) {
		List<Company> companies = companyDao.findAll();
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
	public List<CompanyDTO> findAll(int start, int offset, SortCriteria criteria) {
		List<Company> companies = companyDao.findAll(start, offset);
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
		Company c = companyDao.create(company);
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
	@Transactional
	public void delete(Company company) {
		computerDao.delete(company.getId());

		companyDao.delete(company.getId());
		;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.Service#delete(int)
	 */
	@Override
	public void delete(int id) {
		companyDao.delete(id);
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
		Company c = companyDao.update(company);
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
		return companyDao.count();
	}
}
