package com.verwaltungsplatform.controllers;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.service.UserService;


@Controller
public class UserRegistrationController {
	
	
	private UserService userService;

	
	
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	
	@PostMapping(value = "/registration")
	public ResponseEntity<String> registerUserAccount (@RequestBody Map<String,String> userdata) {
		
		UserRegistrationDto registrationDto = new UserRegistrationDto();
		registrationDto.setEmail(userdata.get("registerEmail"));
		registrationDto.setPassword(userdata.get("registerPassword"));
		registrationDto.setRole(userdata.get("roleCheckedInRegisterForm"));
		registrationDto.setFirstName(userdata.get("registerName"));
		registrationDto.setLastName(userdata.get("registerName"));
		
		userService.save(registrationDto);
		
	    return new ResponseEntity<>(
	    	      "Registration successful", HttpStatus.OK);

	}
	
}
