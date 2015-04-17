package com.excilys.cdb.validation;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.cdb.annotation.Date;

public class DateValidator implements ConstraintValidator<Date, String> {

	private static final String DATE_PATTERN_EN = "MM-dd-yyyy";

	private static final String DATE_PATTERN_FR = "dd-MM-yyyy";

	private final org.apache.commons.validator.routines.DateValidator dateValidator = org.apache.commons.validator.routines.DateValidator
			.getInstance();

	@Override
	public void initialize(Date arg0) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().isEmpty()) {
			return true;
		}
		final Locale locale = LocaleContextHolder.getLocale();
		return dateValidator.isValid(value,
				locale.toString().equals("fr") ? DATE_PATTERN_FR
						: DATE_PATTERN_EN, locale);
	}

}
