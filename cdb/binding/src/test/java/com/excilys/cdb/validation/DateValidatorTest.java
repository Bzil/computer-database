package com.excilys.cdb.validation;

import java.util.Locale;

import junit.framework.TestCase;
import org.junit.Test;

import org.springframework.context.i18n.LocaleContextHolder;

public class DateValidatorTest extends TestCase {


    private static DateValidator validator = new DateValidator();



    @Test
    public void testIsValid() throws Exception {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        assertTrue(validator.isValid("02-29-2008", null));
        assertFalse(validator.isValid("02-29-2009", null));
        LocaleContextHolder.setLocale(Locale.FRENCH);
        assertTrue(validator.isValid("29-02-2008", null));
        assertFalse(validator.isValid("29-02-2009", null));
    }

    @Test
    public void testValidate1() throws Exception {
        assertTrue(validator.validate(9, 9, 1999));
        assertTrue(validator.validate(31, 1, 2001));
        assertTrue(validator.validate(31, 12, 2001));
        assertTrue(validator.validate(29, 2, 2000)); // is leap
        assertTrue(validator.validate(29, 2, 2012)); // is leap
        assertTrue(validator.validate(28, 2, 2011));

        assertFalse(validator.validate(29, 2, 2011)); // is not leap
        assertFalse(validator.validate(31, 4, 2011));
        assertFalse(validator.validate(2, 13, 2011));
        assertFalse(validator.validate(2, 12, 1955));
        assertFalse(validator.validate(32, 12, 2000));
        assertFalse(validator.validate(29, 2, 2100)); // is not leap
    }
}