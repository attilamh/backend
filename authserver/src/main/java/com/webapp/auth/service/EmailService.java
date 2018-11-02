package com.webapp.auth.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;

	private static final Logger logger = Logger.getLogger(EmailService.class.getName());

	public void sendSimpleMessage(String to, String subject, String text) throws MailException {
		logger.info("Registration message:" + text);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

}
