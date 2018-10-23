package com.webapp.auth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	/**
	 * Register a new local user if the email is not duplicated. In that case returns null.
	 * 
	 * @param userDto
	 * @return localUser - the new user or null if already exists
	 */
	public LocalUser registerNewUser(LocalUserDTO userDto) {
		if (isEmailAvailable(userDto.getEmail())) {
			LocalUser localUser = toLocalUser(userDto);
			// disable user until confirmation link clicked
			localUser.setEnabled(false);
			return localUserRepository.save(localUser);
		}
		return null;
	}
	
	public void sendConfirmationEmail(LocalUser user) {
		ConfirmationToken token = createToken(user);
		emailToken(user, token);
	}
	
	private void emailToken(LocalUser user, ConfirmationToken token) {
		String msg = "Hello %s I am sending you the confirmation token. Please click the link to finish the user registration: <a href='https://localhost:8443/uaa/confirm-registration?t=%s'>finish registration</a>";
		String text = String.format(msg, user.getName(), token.getConfirmationToken());
		emailService.sendSimpleMessage(user.getEmail(), "Registration almost complete", text);
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
