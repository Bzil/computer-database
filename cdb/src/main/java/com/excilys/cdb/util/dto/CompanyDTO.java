package com.excilys.cdb.util.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.excilys.cdb.model.Company;

public class CompanyDTO implements DTO<Company>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public int id;

	@NotNull
	@Pattern(regexp = "^[ ]+.*$")
	public String name;

	public static CompanyDTO toDTO(final Company company) {
		final CompanyDTO dto = new CompanyDTO();
		dto.id = company.getId();
		dto.name = company.getName();
		return dto;
	}

	public static Company fromDTO(final CompanyDTO dto) {
		return new Company(dto.id, dto.name);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CompanyDTO other = (CompanyDTO) obj;
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

}
