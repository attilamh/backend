package com.webapp.auth.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.webapp.auth.dto.LocalUserDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
		//
	}

	@Override
	public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
		final LocalUserDTO user = (LocalUserDTO) obj;
		return user.getPassword().equals(user.getConfirmPassword());
	}
}