package com.excilys.cdb.util.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.excilys.cdb.util.validation.Validator;

public class ValidatorTest {

	@Test
	public void testIsCorrectDateCorrect() {
		assertTrue(Validator.INSTANCE.isCorrectDate("01-01-1999"));
	}

	@Test
	public void testIsCorrectDateEmpty() {
		assertFalse(Validator.INSTANCE.isCorrectDate(""));
	}

	@Test
	public void testIsCorrectDateEmptyChar() {
		assertFalse(Validator.INSTANCE.isCorrectDate("       "));
	}

	@Test
	public void testIsCorrectDateNull() {
		assertFalse(Validator.INSTANCE.isCorrectDate(null));
	}

	@Test
	public void testIsCorrectDateWithWrongDay() {
		assertFalse(Validator.INSTANCE.isCorrectDate("34-12-2001"));
	}

	@Test
	public void testIsCorrectDateWithWrongMonth() {
		assertFalse(Validator.INSTANCE.isCorrectDate("21-13-2001"));
	}

	@Test
	public void testIsCorrectDateWithWrongDayAndMonth() {
		assertFalse(Validator.INSTANCE.isCorrectDate("34-13-2001"));
	}

	@Test
	public void testIsCorrectDateWithWrongDateInFebruary() {
		assertFalse(Validator.INSTANCE.isCorrectDate("29-02-2001"));
	}

	@Test
	public void testIsCorrectDateWithGoodDateInFebruary() {
		assertTrue(Validator.INSTANCE.isCorrectDate("29-02-2004"));
	}

	@Test
	public void testIsCorrectDateWithWrongDateInFebruary2() {
		assertFalse(Validator.INSTANCE.isCorrectDate("29-02-2200"));
	}

	@Test
	public void testIsCorrectDateWithWrongSeparator() {
		assertFalse(Validator.INSTANCE.isCorrectDate("34/12/2001"));
	}

	@Test
	public void testIsNumericStringCorrect() {
		assertTrue(Validator.INSTANCE.isNumericString("123"));
	}

	@Test
	public void testIsNumericStringWithNullValue() {
		assertFalse(Validator.INSTANCE.isNumericString(null));
	}

	@Test
	public void testIsNumericStringWithWhiteSpaceString() {
		assertFalse(Validator.INSTANCE.isNumericString("    "));
	}

	@Test
	public void testIsNumericStringWithEmptyString() {
		assertFalse(Validator.INSTANCE.isNumericString(""));
	}

	@Test
	public void testIsNumericStringWithNumericAndWhiteSpaceBefore() {
		assertFalse(Validator.INSTANCE.isNumericString("    123"));
	}

	@Test
	public void testIsNumericStringWithNumericAndWhiteSpaceAfter() {
		assertFalse(Validator.INSTANCE.isNumericString("123      "));
	}

	@Test
	public void testIsNumericStringWithNumericAndWhiteSpaceBeforeAndAfter() {
		assertFalse(Validator.INSTANCE.isNumericString("  123    "));
	}

	@Test
	public void testIsNumericStringWhithCharsAfter() {
		assertFalse(Validator.INSTANCE.isNumericString("123abc"));
	}

	@Test
	public void testIsNumericStringWhithWhithCharsBeforeAndAfter() {
		assertFalse(Validator.INSTANCE.isNumericString("1abc23"));
	}

	@Test
	public void testIsNumericStringWhithWhithCharsBefore() {
		assertFalse(Validator.INSTANCE.isNumericString("abc123"));
	}
}
