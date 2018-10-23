package com.webapp.auth.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	private String confirmationToken;

	@NonNull
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private LocalUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public LocalUser getUser() {
		return user;
	}

	public void setUser(LocalUser user) {
		this.user = user;
	}
}
