package com.excilys.cdb.model;

import java.time.LocalDateTime;

/**
 * The Class Computer.
 */
public class Computer {

	/**
	 * Specific id of a computer.
	 */
	private Integer id;

	/**
	 * The name.
	 */
	private String name;

	/**
	 * The introduced.
	 */
	private LocalDateTime introduced;

	/**
	 * The discontinued.
	 */
	private LocalDateTime discontinued;

	/**
	 * The compagny.
	 */
	private Company company;

	/**
	 * Instantiates a new computer.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introducedgetTime
	 * @param discontinued
	 *            the discontinued
	 * @param company
	 *            the company
	 */
	public Computer(int id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	/**
	 * Instantiates a new computer.
	 *
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introduced
	 * @param discontinued
	 *            the discontinued
	 * @param company
	 *            the company
	 */
	public Computer(String name, LocalDateTime introduced,
			LocalDateTime discontinued, Company company) {
		this(-1, name, introduced, discontinued, company);
	}

	/**
	 * Instantiates a new computer.
	 *
	 * @param name
	 *            the name
	 */
	public Computer(String name) {
		this.name = name;
	}

	/**
	 * Instantiates a new computer.
	 */
	public Computer() {
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
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public LocalDateTime getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced
	 *            the new introduced
	 */
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
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
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company id.
	 *
	 * @param company
	 *            the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued
	 *            the new discontinued
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public static Builder builder(String name) {
		return new Builder(name);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Computer [id=" + id + " name : " + name + " introduced : "
				+ introduced + " discontinued : " + discontinued
				+ " company : " + company + " ]";
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
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
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
		final Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
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

	/**
	 * The Class Builder.
	 */
	public static class Builder {
		private final Computer c;

		private Builder(String name) {
			c = new Computer();
			c.name = name;
		}

		public Builder introduced(LocalDateTime introduced) {
			c.introduced = introduced;
			return this;
		}

		public Builder discontinued(LocalDateTime discontinued) {
			c.discontinued = discontinued;
			return this;
		}

		public Builder company(Company company) {
			c.company = company;
			return this;
		}

		public Builder id(Integer id) {
			c.id = id;
			return this;
		}

		public Computer build() {
			return c;
		}

	}
}
