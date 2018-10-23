package com.webapp.auth.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;

import com.webapp.auth.service.CustomOAuthUserService;

public class CustomOAuthAuthenticationProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {

	private CustomOAuthUserService userService;

	public CustomOAuthAuthenticationProcessingFilter(String defaultFilterProcessesUrl, CustomOAuthUserService userService) {
		super(defaultFilterProcessesUrl);
		this.userService = userService;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);

		userService.loginUser(authResult);
	}
}
