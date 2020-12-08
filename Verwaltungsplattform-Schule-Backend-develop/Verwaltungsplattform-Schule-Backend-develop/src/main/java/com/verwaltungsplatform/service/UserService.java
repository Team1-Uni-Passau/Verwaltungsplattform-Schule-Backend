package com.verwaltungsplatform.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.verwaltungsplatform.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	void save(UserRegistrationDto registrationDto);    
    boolean checkIfUserExist(String email);
}
