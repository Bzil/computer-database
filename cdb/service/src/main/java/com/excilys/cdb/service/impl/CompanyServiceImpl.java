package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.sort.SortCriteria;

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
	private CompanyMapper mapper;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.CompanyService#find(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public CompanyDTO find(final int id) {
		CompanyDTO dto = null;
		if (id > 0) {
			final Company c = companyDao.find(id);
			if (c != null) {
				dto = mapper.toDto(c);
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
	@Transactional(readOnly = true)
	public List<CompanyDTO> findAll(final SortCriteria criteria) {
		final List<Company> companies = companyDao.findAll();
		final List<CompanyDTO> dtos = new ArrayList<>();
		for (final Company c : companies) {
			dtos.add(mapper.toDto(c));
		}

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.CompanyService#findAll(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CompanyDTO> findAll(final int start, final int offset,
			final SortCriteria criteria) {
		final List<Company> companies = companyDao.findAll(start, offset);
		final List<CompanyDTO> dtos = new ArrayList<>();
		for (final Company c : companies) {
			dtos.add(mapper.toDto(c));
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
		this.delete(company.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.excilys.cdb.service.Service#delete(int)
	 */
	@Override
	@Transactional
	public void delete(final int id) {
		computerDao.findByCompanyId(id).stream()
		.forEach(e -> computerDao.delete(e.getId()));

		companyDao.delete(id);
	}

}
