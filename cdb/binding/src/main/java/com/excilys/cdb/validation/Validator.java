package com.excilys.cdb.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Enum Validator.
 */
public enum Validator {
	INSTANCE;

	private static final String DATE_PATTERN = "^(0[1-9]|1[0-9]|2[0-8]|29((?=-([0][13-9]|1[0-2])|(?=-(0[1-9]|1[0-2])-([0-9]{2}(0[48]|[13579][26]|[2468][048])|([02468][048]|[13579][26])00))))|30(?=-(0[13-9]|1[0-2]))|31(?=-(0[13578]|1[02])))-(0[1-9]|1[0-2])-[0-9]{4}$";
	private static final String NUMERIC_PATTERN = "^[0-9]+$";

	private static final String DATE_PATTERN_EN = "^(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-((19|20)\\d\\d)$";

	private static final String DATE_PATTERN_FR = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|20)\\d\\d)$";

	public static Validator getInstance() {
		return INSTANCE;
	}

	public boolean isCorrectDate(String str) {
		return (str != null && !str.isEmpty() && str.matches(DATE_PATTERN));
	}

	public boolean isNumericString(String str) {
		return (str != null && !str.isEmpty() && str.matches(NUMERIC_PATTERN));
	}

	public boolean validate(final String date, final String local) {

		if (date == null || date.trim().isEmpty()) {
			return false;
		}

		final Pattern pattern = Pattern
				.compile(local.equals("fr") ? DATE_PATTERN_FR : DATE_PATTERN_EN);
		final Matcher matcher = pattern.matcher(date);

		if (matcher.matches()) {
			matcher.reset();
			if (matcher.find()) {
				final String day, month;
				final int year;
				if (local.equals("fr")) {
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
						}
					} else if (day.equals("29") || day.equals("30")
							|| day.equals("31")) {
						return false;
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
