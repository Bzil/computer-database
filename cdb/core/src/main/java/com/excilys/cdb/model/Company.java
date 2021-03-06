/**
 * 
 * @author Basile
 */
package com.excilys.cdb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Company.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	/** The name. */
	@Column(name = "name", nullable = true, length = 255)
	private String name;

	/**
	 * Instantiates a new company.
	 */
	public Company() {
	}

	/**
	 * Instantiates a new company.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Instantiates a new company.
	 *
	 * @param name the name
	 */
	public Company(String name) {
		this(-1, name);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Company other = (Company) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {

		/** The c. */
		private final Company c;

		/**
		 * Instantiates a new builder.
		 */
		private Builder() {
			c = new Company();
		}

		/**
		 * Id.
		 *
		 * @param id the id
		 * @return the builder
		 */
		public Builder id(Integer id) {
			c.id = id;
			return this;
		}

		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the builder
		 */
		public Builder name(String name) {
			c.name = name;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the company
		 */
		public Company build() {
			return c;
		}
	}
}
