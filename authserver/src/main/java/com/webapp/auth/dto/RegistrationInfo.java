package com.webapp.auth.dto;

import com.webapp.auth.entity.LocalUser;

public class RegistrationInfo {
	LocalUser localUser;
	String registrationMessage;
	public LocalUser getLocalUser() {
		return localUser;
	}
	public void setLocalUser(LocalUser localUser) {
		this.localUser = localUser;
	}
	public String getRegistrationMessage() {
		return registrationMessage;
	}
	public void setRegistrationMessage(String registrationMessage) {
		this.registrationMessage = registrationMessage;
	}
}
