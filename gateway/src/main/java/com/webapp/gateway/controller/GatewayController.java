package com.webapp.gateway.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

	@RequestMapping("/")
	public String home(Principal user) {
		return "Hello " + user.getName();
	}

	@RequestMapping("/kata")
	public Message kata() {
		return new Message("Hello Kata");
	}

	@RequestMapping({ "/user", "/me" })
	public Map<String, String> user(Principal user) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", user.getName());
		return map;
	}

	class Message {
		private String id = UUID.randomUUID().toString();
		private String content;

		Message() {
		}

		public Message(String content) {
			this.content = content;
		}

		public String getId() {
			return id;
		}

		public String getContent() {
			return content;
		}
	}
}
