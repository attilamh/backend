package com.webapp.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.auth.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
