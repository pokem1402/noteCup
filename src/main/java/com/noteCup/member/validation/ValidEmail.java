package com.noteCup.member.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


//https://www.baeldung.com/java-custom-annotation

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * Validation for Email
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.validation
 * @File		: ValidEmail.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: ValidEmail
 * @version		: 1.0
 * @formatter:on
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
	String message() default "Invalid email";
	Class<?> [] groups() default {};
	Class<? extends Payload>[] payload() default{};

}