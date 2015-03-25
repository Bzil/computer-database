package com.excilys.cdb.util.validator;

/**
 * The Enum Validator.
 */
public enum Validator {
	INSTANCE;

	private static final String DATE_PATTERN = "^(0[1-9]|1[0-9]|2[0-8]|29((?=-([0][13-9]|1[0-2])|(?=-(0[1-9]|1[0-2])-([0-9]{2}(0[48]|[13579][26]|[2468][048])|([02468][048]|[13579][26])00))))|30(?=-(0[13-9]|1[0-2]))|31(?=-(0[13578]|1[02])))-(0[1-9]|1[0-2])-[0-9]{4}$";
	private static final String NUMERIC_PATTERN = "^[0-9]+$";

	public static Validator getInstance() {
		return INSTANCE;
	}

	public boolean isCorrectDate(String str) {
		return (str != null && !str.isEmpty() && str.matches(DATE_PATTERN));
	}

	public boolean isNumericString(String str) {
		return (str != null && !str.isEmpty() && str.matches(NUMERIC_PATTERN));
	}

}
