package com.webapp.auth.controller;

import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.auth.dto.LocalUserDTO;
import com.webapp.auth.dto.RegistrationInfo;
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
	public RegistrationInfo showRegistrationForm(@Valid @RequestBody LocalUserDTO userDto) {
		// TODO check if unique email in registration can be done using validation
		// TODO continue registration process: https://localhost:8443/uaa/confirm-registration?t=998e5e8b-cc5a-415a-aa97-ca8428e72ac7
		logger.info(userDto.toString());
		Optional<LocalUser> optionalUser = registrationService.registerNewUser(userDto);
		RegistrationInfo registrationInfo = new RegistrationInfo();
		if (optionalUser.isPresent()) {
			LocalUser localUser = optionalUser.get();
			Optional<String> registrationMessage = registrationService.sendConfirmationEmail(localUser);
			registrationInfo.setLocalUser(localUser);
			if (registrationMessage.isPresent()) {
				registrationInfo.setRegistrationMessage(registrationMessage.get());
			}
		}
		else {
			// TODO research how error messages are managed/localized
			registrationInfo.setRegistrationMessage("ERROR:EMAIL_EXISTS");
		}
		return registrationInfo;
	}
	
}
