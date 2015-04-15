package com.excilys.cdb.util.validation;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.dto.ComputerDTO;

public class ComputerValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Computer.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		final ComputerDTO computer = (ComputerDTO) arg0;

		if (DateValidator.validate(computer.introduced, LocaleContextHolder
				.getLocale().toString())) {
			arg1.rejectValue("introduced", "Wrong date");
			// TODO use msg language
		}

		if (DateValidator.validate(computer.discontinued, LocaleContextHolder
				.getLocale().toString())) {
			// TODO use msg language
			arg1.rejectValue("discontinued", "Wrong date");
		}

	}

}
