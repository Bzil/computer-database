package com.excilys.cdb.mapper;

import com.excilys.cdb.dto.DTO;

/**
 * The Interface DtoMapper.
 *
 * @param <T> the generic type
 * @param <D> the generic type
 */
public interface DtoMapper<T, D extends DTO<T>> {

    /**
     * To dto.
     *
     * @param t the t
     * @return the d
     */
    D toDto(T t);

    /**
     * To model.
     *
     * @param dto the dto
     * @return the t
     */
    T toModel(D dto);

}
