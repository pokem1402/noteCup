package com.noteCup.member.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.noteCup.member.model.dto.MemberInput;


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