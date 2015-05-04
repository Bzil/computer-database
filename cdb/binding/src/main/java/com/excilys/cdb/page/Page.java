/**
 *
 * @author Basile
 */
package com.excilys.cdb.page;

import java.util.List;

/**
 * The Class Page.
 *
 * @param <T>
 *            the generic type
 */
public abstract class Page<T> {

	/** The Constant OFFSET_PAGE. */
	private static final int OFFSET_PAGE = 3;

	/** The entities. */
	private List entities;

	/** The start. */
	private int start = 0;

	/** The offset. */
	private int offset = 0;

	/** The count. */
	private long count;

	/** The current page. */
	private int currentPage = 1;

	/** The page nb. */
	private int pageNb;

	/** The start page. */
	private int startPage = 0;

	/** The end page. */
	private int endPage = 4;

	/** The search. */
	private String search;

	/** The order by. */
	private String orderBy = "DESC";

	/** The column. */
	private String column = "";

	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public List getEntities() {
		return entities;
	}

	/**
	 * Show entities.
	 *
	 * @param list
	 *            the list
	 */
	public void showEntities(List<T> list) {
		for (final T t : list) {
			System.out.println(t);
		}
	}

	/**
	 * Sets the entities.
	 *
	 * @param entities
	 *            the new entities
	 */
	public void setEntities(List entities) {
		this.entities = entities;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset.
	 *
	 * @param offset
	 *            the new offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage
	 *            the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.start = (currentPage > 1) ? (currentPage - 1) * offset : 0;
	}

	/**
	 * Gets the page nb.
	 *
	 * @return the page nb
	 */
	public int getPageNb() {
		return pageNb;
	}

	/**
	 * Sets the page nb.
	 *
	 * @param pageNb
	 *            the new page nb
	 */
	private void setPageNb(int pageNb) {
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

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count
	 *            the new count
	 */
	public void setCount(long count) {
		this.count = count;
		final int nb = (int) count / offset;
		setPageNb(count < offset ? 1 : count % nb == 0 ? nb : nb + 1);
	}

	/**
	 * Gets the start page.
	 *
	 * @return the start page
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * Gets the end page.
	 *
	 * @return the end page
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Sets the search.
	 *
	 * @param search
	 *            the new search
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy
	 *            the new order by
	 */
	public void setOrderBy(String orderBy) {
		if (orderBy.equals("ASC")) {
			this.orderBy = orderBy;
		} else {
			this.orderBy = "DESC";
		}
	}

	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * Sets the column.
	 *
	 * @param column
	 *            the new column
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Page [start=" + start + ", offset=" + offset + ", count=" + count + ", currentPage=" + currentPage
				+ ", pageNb=" + pageNb + ", startPage=" + startPage + ", endPage=" + endPage + ", search=" + search
				+ ", orderBy=" + orderBy + ", column=" + column + "]";
	}

}