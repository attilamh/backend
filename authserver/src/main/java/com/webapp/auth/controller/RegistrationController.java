package com.webapp.auth.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.auth.dto.LocalUserDTO;
import com.webapp.auth.entity.LocalUser;
import com.webapp.auth.service.RegistrationService;

@Controller
public class RegistrationController {

	private static final Logger logger = Logger.getLogger(RegistrationController.class.getName());

	@Autowired
	RegistrationService registrationService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm() {
	    return "registration.html";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ResponseBody
	public LocalUser showRegistrationForm(@Valid @RequestBody LocalUserDTO userDto) {
		logger.info(userDto.toString());
		LocalUser localUser = registrationService.registerNewUser(userDto);
		if (localUser != null) {
			registrationService.sendConfirmationEmail(localUser);
		}
		return localUser;
	}
	
}
