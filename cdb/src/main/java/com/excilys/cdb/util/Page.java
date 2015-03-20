package com.excilys.cdb.util;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.service.Service;
import com.excilys.cdb.servlet.dto.CompanyDTO;
import com.excilys.cdb.servlet.dto.ComputerDTO;
import com.excilys.cdb.servlet.dto.DTO;

/**
 * The Class Page.
 *
 * @param <T>
 *            the generic type
 */
public abstract class Page<T> {

	/** The entities. */
	private List<DTO<T>> entities = new ArrayList<>();

	/** The from index. */
	private int start = 0;

	/** The to index. */
	private int offset = 0;
	
	private Service<T> service  = getService(); 

	/**
	 * Paginate.
	 *
	 * @param entites
	 *            the entites
	 * @param start
	 *            the from index
	 * @param offset
	 *            the to index
	 * @return the list
	 */
	public List<DTO<T>> paginate(int start, int offset) {
		if (start < 0 || start > offset) {
			throw new IndexOutOfBoundsException();
		}
			
		for ( T t : service.findAll(start, offset)){
			entities.add(getDTO(t));
		}
		return entities;	
			
	}

	protected abstract DTO<T> getDTO(T t);
	
	protected abstract Service<T> getService();

	/**
	 * Show paginated list.
	 *
	 * @param entities
	 *            the entities
	 */
	public void showPaginatedList(List<T> entities) {
		for (int i = 0; i < entities.size(); i++) {
			System.out.println(entities.get(i));
		}
	}

	public List<DTO<T>> getEntities() {
		return entities;
	}

	public void setEntities(List<DTO<T>> entities) {
		this.entities = entities;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setService(Service<T> service) {
		this.service = service;
	}
	
	

}