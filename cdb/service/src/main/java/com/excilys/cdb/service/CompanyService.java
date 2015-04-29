package com.excilys.cdb.service;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.sort.SortCriteria;

import java.util.List;

/**
 * The Interface CompanyService.
 */
public interface CompanyService extends ServiceCommons<Company, CompanyDTO> {

    /**
     * Find.
     *
     * @param id the id
     * @return the company
     */
    @Override
    public CompanyDTO find(int id);

    /**
     * Find all.
     *
     * @return the list
     */
    @Override
    public List<CompanyDTO> findAll(SortCriteria criteria);

    /**
     * Find all.
     *
     * @param start  the start
     * @param offset the offset
     * @return the list
     */
    @Override
    public List<CompanyDTO> findAll(int start, int offset, SortCriteria criteria);

    /**
     * Delete.
     *
     * @param company the company
     */
    @Override
    public void delete(Company company);

}
