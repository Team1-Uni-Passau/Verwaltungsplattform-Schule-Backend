package com.verwaltungsplatform.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.verwaltungsplatform.dto.UserInfoDto;
import com.verwaltungsplatform.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	ResponseEntity<String> save(UserRegistrationDto registrationDto);    
    boolean checkIfUserExist(String email);
    String getUserRole(String email);
    int getUserId (String email);
    boolean checkIfRegisterCodeMatchesRole(String role, int registrationCode);
    List<UserInfoDto> GetAllStudents();
    List<UserInfoDto> GetAllTeachers();

}
