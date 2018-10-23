package com.webapp.auth.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.webapp.auth.validation.PasswordMatches;
import com.webapp.auth.validation.ValidEmail;
import com.webapp.auth.validation.ValidPassword;

@PasswordMatches
public class LocalUserDTO {

	@NotNull
	@NotEmpty
	private String name;

	@ValidPassword
	private String password;

	@NotNull
	@NotEmpty
	private String confirmPassword;

	@ValidEmail
	@NotNull
	@NotEmpty
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
