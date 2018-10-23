package com.webapp.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.webapp.auth.service.CustomOAuthSsoService;

@EnableWebSecurity
@EnableOAuth2Client
@EnableAuthorizationServer
public class AuthServerConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomOAuthSsoService ssoService;
		
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.antMatcher("/**").authorizeRequests()
				.antMatchers("/index.html", "/", "/sso**", "/webjars/**", "/error**", "/js.cookie.js", "/login", "/registration")
				.permitAll().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().formLogin()
				.defaultSuccessUrl("/").permitAll().and().logout().logoutSuccessUrl("/").permitAll().and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				.addFilterBefore(ssoService.getOAuthFilter(), BasicAuthenticationFilter.class);
		// @formatter:on
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder(8);
		return encoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String result = passwordEncoder().encode("password");
		auth.inMemoryAuthentication().withUser("user").password(result).roles("USER").and().withUser("admin")
				.password(result).roles("ADMIN");
	}

	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
			OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

}
