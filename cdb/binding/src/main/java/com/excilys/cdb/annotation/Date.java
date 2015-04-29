package com.excilys.cdb.annotation;

import com.excilys.cdb.validation.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateValidator.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
    String message() default "{validation.date}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
