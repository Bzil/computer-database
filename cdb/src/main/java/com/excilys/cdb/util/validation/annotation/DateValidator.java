package com.excilys.cdb.util.validation.annotation;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

public class DateValidator implements ConstraintValidator<Date, String> {

	private static final String DATE_PATTERN_EN = "^(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-((19|20)[0-9]{2})$";

	private static final String DATE_PATTERN_FR = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|20)[0-9]{2})$";

	@Override
	public void initialize(Date arg0) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().isEmpty()) {
			return true;
		}
		final Locale locale = LocaleContextHolder.getLocale();
		return validate(value.trim(), locale.toString());
	}

	private boolean validate(final String date, final String locale) {
		if (date == null || date.trim().isEmpty()) {
			return true;
		}

		final Pattern pattern = Pattern
				.compile(locale.equals("fr") ? DATE_PATTERN_FR
						: DATE_PATTERN_EN);
		final Matcher matcher = pattern.matcher(date);

		if (matcher.matches()) {
			matcher.reset();
			if (matcher.find()) {
				final String day, month;
				final int year;
				if (locale.equals("fr")) {
					day = matcher.group(1);
					month = matcher.group(2);
				} else {
					month = matcher.group(1);
					day = matcher.group(2);
				}
				year = Integer.parseInt(matcher.group(3));
				if (day.equals("31")
						&& (month.equals("11") || month.equals("04")
								|| month.equals("06") || month.equals("09"))) {
					return false;
				} else if (month.equals("02")) {

					if (isLeapYear(year)) {
						if (day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					} else if (day.equals("29") || day.equals("30")
							|| day.equals("31")) {
						return false;
					} else {
						return true;
					}
				}

			} else {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	private boolean isLeapYear(final int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
}
