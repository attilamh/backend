package com.webapp.auth.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class CustomOAuthUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	@Column(unique = true, length = 200)
	private String thirdPartyId;

	@NonNull
	private String oauthVendor;

	private String email;

	private String name;

	@NonNull
	private LocalDateTime firstLogin;

	@NonNull
	private LocalDateTime lastLogin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}

	public String getOauthVendor() {
		return oauthVendor;
	}

	public void setOauthVendor(String oauthVendor) {
		this.oauthVendor = oauthVendor;
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

	public LocalDateTime getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(LocalDateTime firstLogin) {
		this.firstLogin = firstLogin;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

}