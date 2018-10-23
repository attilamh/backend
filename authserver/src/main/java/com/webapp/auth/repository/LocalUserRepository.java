package com.webapp.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.auth.entity.LocalUser;

public interface LocalUserRepository extends CrudRepository<LocalUser, Long> {
	LocalUser findByEmail(String email);
}
