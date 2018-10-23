package com.webapp.main.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

	@RequestMapping("/")
	public Message index() {
		return new Message("Hello Rozsi");
	}

	@RequestMapping("/home")
	public Message home() {
		return new Message("Hello Zsuzsi");
	}
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