package com.excilys.cdb.util.dto;

import com.excilys.cdb.model.Company;

public class CompanyDTO implements DTO<Company> {

	public int id;
	public String name;

	public static CompanyDTO toDTO(Company company) {
		CompanyDTO dto = new CompanyDTO();
		dto.id = company.getId();
		dto.name = company.getName();
		return dto;
	}

	public static Company fromDTO(CompanyDTO dto) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
