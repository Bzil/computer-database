package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
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
	public CompanyDTO find(final int id) {
		CompanyDTO dto = null;
		if (id > 0) {
			final Company c = companyDao.find(id);

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
	public List<CompanyDTO> findAll(final SortCriteria criteria) {
		final List<Company> companies = companyDao.findAll();
		final List<CompanyDTO> dtos = new ArrayList<>();
		for (final Company c : companies) {
			dtos.add(CompanyDTO.toDTO(c));
		}

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#findAll(int, int)
	 */
	@Override
	public List<CompanyDTO> findAll(final int start, final int offset,
			final SortCriteria criteria) {
		final List<Company> companies = companyDao.findAll(start, offset);
		final List<CompanyDTO> dtos = new ArrayList<>();
		for (final Company c : companies) {
			dtos.add(CompanyDTO.toDTO(c));
		}

		return dtos;
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
	public void delete(final Company company) {

		/*
		 * final List<Computer> computers =
		 * computerDao.findByCompanyId(company.getId()); for (Computer computer
		 * : computers) { computerDao.delete(computer.getId()); }
		 */
		computerDao.delete(company.getId());

		companyDao.delete(company.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.Service#delete(int)
	 */
	@Override
	public void delete(final int id) {
		companyDao.delete(id);
	}

}
