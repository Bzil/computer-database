package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;



/**
 * The Class Page.
 *
 * @param <T>
 * the generic type
 */
public class Page<T> {
	
	/** The entities. */
	private List<T> entities = new ArrayList<T>();
	
	/** The from index. */
	private int start = 0;
	
	/** The to index. */
	private int offset = 0;
	
	/**
	 * Paginate.
	 *
	 * @param entites the entites
	 * @param start the from index
	 * @param offset the to index
	 * @return the list
	 */
	public List<T> paginate(List<T> entites, int start, int offset) {
		if (offset > entites.size()){
			offset = entites.size();
		}
		if (start < 0 || start > offset) {
			throw new IndexOutOfBoundsException();
		}
		return entites.subList(start, offset);
	}
	/**
	 * Show paginated list.
	 *
	 * @param entities
	 * the entities
	 */
	public void showPaginatedList(List<T> entities) {
		for (int i = 0; i < entities.size(); i++) {
			System.out.println(entities.get(i));
		}
	}
	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public List<T> getEntities() {
		return entities;
	}
	/**
	 * Sets the entities.
	 *
	 * @param entities
	 * the new entities
	 */
	public void setEntities(List<T> entities) {
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
	
	
}