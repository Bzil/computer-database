package com.excilys.cdb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.dto.DTO;

/**
 * The Interface DtoMapper.
 *
 * @param <T>
 *            the generic type
 * @param <D>
 *            the generic type
 */
public interface DtoMapper<T, D extends DTO<T>> {

	/**
	 * To dto.
	 *
	 * @param t
	 *            the t
	 * @return the d
	 */
	public D toDto(T t);

	/**
	 * To model.
	 *
	 * @param dto
	 *            the dto
	 * @return the t
	 */
	public T toModel(D dto);

	/**
	 * To dtos.
	 *
	 * @param list
	 *            the list
	 * @return the list
	 */
	default List<D> toDtos(List<T> list) {
		return list.stream().map(this::toDto).collect(Collectors.toList());
	}

	/**
	 * To models.
	 *
	 * @param list
	 *            the list
	 * @return the list
	 */
	default List<T> toModels(List<D> list) {
		return list.stream().map(this::toModel).collect(Collectors.toList());
	}

}
