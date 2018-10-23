package com.webapp.auth.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class LocalUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	@Column(unique=true, length=200)
	private String email;

	private String name;

	@NonNull
	private String password;
	
	@NonNull
	private LocalDateTime registrationDate;

	@NonNull
	private boolean enabled;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
