package com.verwaltungsplatform.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.verwaltungsplatform.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	ResponseEntity<String> save(UserRegistrationDto registrationDto);    
    boolean checkIfUserExist(String email);
    String getUserRole(String email);
    boolean checkIfRegisterCodeMatchesRole(String role, int registrationCode);

}
