package com.excilys.cdb.api;

import com.excilys.cdb.dto.CompanyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyApi {

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     */
    ResponseEntity<CompanyDTO> getById(Integer id);

    /**
     * Gets the all.
     *
     * @return the all
     */
    ResponseEntity<List<CompanyDTO>> getAll();

    /**
     * Delete.
     *
     * @param id the id
     * @return the response
     */
    ResponseEntity<CompanyDTO> delete(Integer id);
}
