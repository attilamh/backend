package com.webapp.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.auth.entity.CustomOAuthUser;

public interface CustomOAuthUserRepository extends CrudRepository<CustomOAuthUser, Long> {
    CustomOAuthUser findByThirdPartyIdAndOauthVendor(String thirdPartyId, String oauthVendor);
}
