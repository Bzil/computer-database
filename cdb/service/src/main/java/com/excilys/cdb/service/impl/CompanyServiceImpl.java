package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

	/**
	 * The dao.
	 */
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
		LOGGER.info("Find company {}", id);
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
		LOGGER.info("Find all companies");
		final List<CompanyDTO> dtos = new ArrayList<>();
		companyDao.findAll().stream().forEach(c -> dtos.add(mapper.toDto(c)));

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.service.CompanyService#findAll(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CompanyDTO> findAll(final int start, final int offset, final SortCriteria criteria) {
		LOGGER.info("Find all companies {} - {}", start, offset);
		final List<CompanyDTO> dtos = new ArrayList<>();
		companyDao.findAll(start, offset).stream().forEach(c -> dtos.add(mapper.toDto(c)));
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
		LOGGER.info("Delete company {}", company);
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
		LOGGER.info("Delete company {}", id);
		computerDao.findByCompanyId(id).stream().forEach(e -> computerDao.delete(e.getId()));

		companyDao.delete(id);
	}

}
