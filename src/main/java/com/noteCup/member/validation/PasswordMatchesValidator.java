package com.noteCup.member.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.noteCup.member.model.dto.MemberInput;


/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.validation
 * @File		: PasswordMatchesValidator.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: PasswordMatchesValidator
 * @version		: 
 * @formatter:on
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constatintAnnoTation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		MemberInput user = (MemberInput) obj;
		return user.getPassword().equals(user.getMatchingPassword());
		
	}
}