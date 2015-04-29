package com.excilys.cdb.mapper.impl;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ComputerMapperImpl implements ComputerMapper {

    @Autowired
    private CompanyMapper mapper;

    @Override
    public ComputerDTO toDto(Computer t) {
        final DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd-MM-yyyy");
        final ComputerDTO dto = new ComputerDTO();
        dto.id = t.getId();
        dto.name = t.getName();
        if (t.getIntroduced() != null) {
            dto.introduced = t.getIntroduced().format(formatter);
        } else {
            dto.introduced = "";
        }
        if (t.getDiscontinued() != null) {
            dto.discontinued = t.getDiscontinued().format(formatter);
        } else {
            dto.discontinued = "";
        }
        if (t.getCompany() != null) {
            dto.company = mapper.toDto(t.getCompany());
        } else {
            dto.company = null;
        }
        return dto;
    }

    @Override
    public Computer toModel(ComputerDTO dto) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd-MM-uuuu HH:mm:ss", new Locale("fr"));
        final Computer computer = new Computer();

        computer.setId(dto.id);
        computer.setName(dto.name);
        if (dto.introduced != null && !dto.introduced.trim().isEmpty()) {
            computer.setIntroduced(LocalDateTime.parse(
                    dto.introduced += " 00:00:00", formatter));
        }
        if (dto.discontinued != null && !dto.discontinued.trim().isEmpty()) {
            computer.setDiscontinued(LocalDateTime.parse(
                    dto.discontinued += " 00:00:00", formatter));
        }

        if (dto.company != null && !dto.company.name.trim().isEmpty()) {
            computer.setCompany(new Company(dto.company.id, dto.company.name
                    .trim()));
        }

        return computer;
    }

}
