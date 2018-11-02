package com.webapp.auth.service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.auth.dto.LocalUserDTO;
import com.webapp.auth.entity.ConfirmationToken;
import com.webapp.auth.entity.LocalUser;
import com.webapp.auth.repository.ConfirmationTokenRepository;
import com.webapp.auth.repository.LocalUserRepository;

@Service
public class RegistrationService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LocalUserRepository localUserRepository;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailService emailService;

	private static final Logger logger = Logger.getLogger(EmailService.class.getName());

	/**
	 * Register a new local user if the email is not duplicated.
	 * 
	 * @param userDto
	 * @return localUser - the new user or empty optional if user does not exist
	 */
	public Optional<LocalUser> registerNewUser(LocalUserDTO userDto) {
		LocalUser localUser = null;
		if (isEmailAvailable(userDto.getEmail())) {
			localUser = toLocalUser(userDto);
			// disable user until confirmation link clicked
			localUser.setEnabled(false);
			localUser = localUserRepository.save(localUser);
		}
		return Optional.ofNullable(localUser);
	}

	public Optional<String> sendConfirmationEmail(LocalUser user) throws MailException {
		ConfirmationToken token = createToken(user);
		return emailToken(user, token);
	}

	private Optional<String> emailToken(LocalUser user, ConfirmationToken token) {
		// TODO Need to move this into a template and internationalize
		String msg = "Hello %s \n Here is the confirmation token. Please click the link to finish the user registration: <a href='https://localhost:8443/uaa/confirm-registration?t=%s'>finish registration</a>";
		Optional<String> registrationMessage = Optional.empty();
		String text = String.format(msg, user.getName(), token.getConfirmationToken());
		registrationMessage = Optional.ofNullable(text);
		try {
			emailService.sendSimpleMessage(user.getEmail(), "Registration almost complete", text);
		} catch (Exception e) {
			// if sending email ran into exception return token message
			// so registration can be completed
			logger.info(e.getMessage());
		}
		return registrationMessage;
	}

	private ConfirmationToken createToken(LocalUser user) {
		ConfirmationToken confirmationToken = new ConfirmationToken();
		// Generate random 36-character string token for confirmation link
		confirmationToken.setConfirmationToken(UUID.randomUUID().toString());
		confirmationToken.setUser(user);
		return confirmationTokenRepository.save(confirmationToken);
	}

	private LocalUser toLocalUser(LocalUserDTO userDto) {
		LocalUser user = new LocalUser();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(encryptPassword(userDto.getPassword()));
		return user;
	}

	private boolean isEmailAvailable(String email) {
		LocalUser user = localUserRepository.findByEmail(email);
		return (user == null);
	}

	private String encryptPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
