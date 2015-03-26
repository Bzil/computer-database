package com.excilys.cdb.util;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.util.dto.DTO;

/**
 * The Class Page.
 *
 * @param <T>
 *            the generic type
 */
public abstract class Page<T, D extends DTO<T>> {

	/** The entities. */
	private List<D> entities = new ArrayList<>();

	/** The index of T in base. */
	private int start = 0;

	/** The to index. */
	private int offset = 0;

	/** Count of items */
	private int count;

	/** The current page. */
	private int currentPage = 1;

	/** The page number */
	private int pageNb;

	private int startPage = 0;

	private int endPage = 4;

	private static final int OFFSET_PAGE = 3;

	protected abstract DTO<T> getDTO(T t);

	public void showEntities(List<D> list) {
		for (D d : list) {
			System.out.println(d);
		}
	}

	public List<D> getEntities() {
		return entities;
	}

	public void setEntities(List<D> entities) {
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;

	}

	public int getPageNb() {
		return pageNb;
	}

	public void setPageNb(int pageNb) {
		this.pageNb = pageNb;
		if (currentPage - OFFSET_PAGE > 0) {
			this.startPage = currentPage - OFFSET_PAGE;
		} else {
			this.startPage = 0;
		}
		if (currentPage + OFFSET_PAGE < pageNb) {
			this.endPage = currentPage + OFFSET_PAGE;
		} else {
			this.endPage = pageNb;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	@Override
	public String toString() {
		return "Page [start=" + start + ", offset=" + offset + ", count="
				+ count + ", currentPage=" + currentPage + ", pageNb=" + pageNb
				+ ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	

}