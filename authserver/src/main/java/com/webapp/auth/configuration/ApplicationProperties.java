package com.webapp.auth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

	@Value("${custom-oauth.login.prefix}")
	private String loginPrefix;
	
	public void setLoginPrefix(String loginPrefix) {
		this.loginPrefix = loginPrefix;
	}
	public String getLoginPrefix() {
		return loginPrefix;
	}
	
	public String getLoginPath(String provider) {
		return String.join("", getLoginPrefix(), provider);
	}
	
}
