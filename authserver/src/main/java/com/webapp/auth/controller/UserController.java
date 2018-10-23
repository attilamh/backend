package com.webapp.auth.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.auth.entity.CustomOAuthUser;

@RestController
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class.getName());

	@RequestMapping("/full-user")
	public Principal fullUser(Principal user) {
		return user;
	}

	@RequestMapping({ "/user", "/me" })
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		
		if (principal instanceof OAuth2Authentication) {
			CustomOAuthUser user = (CustomOAuthUser) ((OAuth2Authentication)principal).getPrincipal();
			logger.info("User name:" + user.getName());
			map.put("name", user.getName());
		}
		else {
			logger.info("User name:" + principal.getName());
			map.put("name", principal.getName());
		}
		return map;
	}
}
