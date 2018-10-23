package com.webapp.auth.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.webapp.auth.configuration.CustomOAuthClientResources.AuthenticationKeys;
import com.webapp.auth.entity.CustomOAuthUser;
import com.webapp.auth.repository.CustomOAuthUserRepository;

@Service
public class CustomOAuthUserService {

	@Autowired
	private CustomOAuthUserRepository userRepository;

	public CustomOAuthUser initializeUser(String provider, Map<String, Object> map, AuthenticationKeys authenticationKeys) {
		String nameKey = authenticationKeys.getNameKey();
		String emailKey = authenticationKeys.getEmailKey();
		String thirdPartyIdKey = authenticationKeys.getThirdPartyIdKey();

		String thirdPartyId = String.valueOf(map.get(thirdPartyIdKey));
		CustomOAuthUser user = null;
		user = userRepository.findByThirdPartyIdAndOauthVendor(thirdPartyId, provider);

		if (user == null) {
			user = new CustomOAuthUser();
			user.setFirstLogin(LocalDateTime.now());
		}
		user.setName((String) map.get(nameKey));
		user.setEmail((String) map.get(emailKey));
		user.setThirdPartyId(thirdPartyId);
		user.setOauthVendor(provider);
		return user;
	}

	public void loginUser(Authentication authentication) {
		CustomOAuthUser user = (CustomOAuthUser) authentication.getPrincipal();
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}
}
