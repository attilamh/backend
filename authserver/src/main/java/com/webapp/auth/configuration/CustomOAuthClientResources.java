package com.webapp.auth.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class CustomOAuthClientResources {

	@NestedConfigurationProperty
	private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	@NestedConfigurationProperty
	private ResourceServerProperties resource = new ResourceServerProperties();

	@NestedConfigurationProperty
	private AuthenticationKeys authenticationKeys = new AuthenticationKeys();

	public AuthorizationCodeResourceDetails getClient() {
		return client;
	}

	public ResourceServerProperties getResource() {
		return resource;
	}

	public AuthenticationKeys getAuthenticationKeys() {
		return authenticationKeys;
	}
	
	public class AuthenticationKeys {
		
		private String thirdPartyIdKey = "id";

		private String nameKey = "name";

		private String emailKey = "email";

		public void setThirdPartyIdKey(String thirdPartyIdKey) {
			this.thirdPartyIdKey = thirdPartyIdKey;
		}

		public String getThirdPartyIdKey() {
			return thirdPartyIdKey;
		}

		public void setNameKey(String nameKey) {
			this.nameKey = nameKey;
		}

		public String getNameKey() {
			return nameKey;
		}

		public void setEmailKey(String emailKey) {
			this.emailKey = emailKey;
		}

		public String getEmailKey() {
			return emailKey;
		}
	}
}
