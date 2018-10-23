package com.webapp.auth.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("custom-oauth")
public class CustomOAuthProviders {

	private Map<String, CustomOAuthClientResources> providers = new HashMap<>();
	
	public CustomOAuthClientResources getClientResource(String authProvider) {
		return this.providers.get(authProvider);
	}
	
	public Map<String, CustomOAuthClientResources> getProviders() {
		return this.providers;
	}

	public Set<String> getProviderNames() {
		return this.providers.keySet();
	}
}
