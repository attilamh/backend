package com.webapp.auth.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CompositeFilter;

import com.webapp.auth.configuration.ApplicationProperties;
import com.webapp.auth.configuration.CustomOAuthAuthenticationProcessingFilter;
import com.webapp.auth.configuration.CustomOAuthClientResources;
import com.webapp.auth.configuration.CustomOAuthClientResources.AuthenticationKeys;
import com.webapp.auth.configuration.CustomOAuthProviders;

@Service
public class CustomOAuthSsoService {
	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Autowired
	CustomOAuthUserService userService;

	@Autowired
	ApplicationProperties properties;

	@Autowired
	CustomOAuthProviders customOAuthProviders;
	
	public Filter getOAuthFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		customOAuthProviders.getProviderNames().forEach(provider -> filters.add(getFilter(provider)));
		filter.setFilters(filters);
		return filter;
	}

	private Filter getFilter(String provider) {
		CustomOAuthClientResources client = getClientResource(provider);
		CustomOAuthAuthenticationProcessingFilter filter = new CustomOAuthAuthenticationProcessingFilter(
				properties.getLoginPath(provider), userService);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
				client.getClient().getClientId());
		tokenServices.setRestTemplate(template);
		AuthenticationKeys authenticationKeys = client.getAuthenticationKeys();
		tokenServices.setPrincipalExtractor(map -> userService.initializeUser(provider, map, authenticationKeys));
		filter.setTokenServices(tokenServices);

		return filter;
	}

	private CustomOAuthClientResources getClientResource(String ssoProvider) {
		return customOAuthProviders.getClientResource(ssoProvider);
	}
}
