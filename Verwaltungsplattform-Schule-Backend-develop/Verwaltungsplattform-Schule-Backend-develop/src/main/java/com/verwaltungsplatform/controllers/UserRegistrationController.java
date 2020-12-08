package com.verwaltungsplatform.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.verwaltungsplatform.dto.AuthRequestDto;
import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.exceptions.UserAlreadyExistException;
import com.verwaltungsplatform.service.UserService;
import com.verwaltungsplatform.util.JwtUtil;

@Controller
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authentificationManager;
	
	
	
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	
	
	// Method to handle registration
	@PostMapping(value = "/registration")
	public ResponseEntity<String> registerUserAccount (@RequestBody Map<String,String> userdata) throws UserAlreadyExistException {
		
		UserRegistrationDto registrationDto = new UserRegistrationDto();
		
		registrationDto.setEmail(userdata.get("registerEmail"));
		registrationDto.setPassword(userdata.get("registerPassword"));
		registrationDto.setRole(userdata.get("roleCheckedInRegisterForm"));
		registrationDto.setFirstName(userdata.get("registerName"));
		registrationDto.setLastName(userdata.get("registerName"));
    	
		
		try {
			userService.save(registrationDto);
		} catch (UserAlreadyExistException e) {	
		    return new ResponseEntity<>(
		    	      "This user might already be saved in the database", HttpStatus.CONFLICT);
		}
		
	    return new ResponseEntity<>(
	    	      "Registration successful", HttpStatus.OK);

	}
	
	
	@PostMapping(value= "/login")
	public ResponseEntity<String> generateToken(@RequestBody Map<String,String> loginData) throws Exception {
		
		AuthRequestDto authRequestDto = new AuthRequestDto(loginData.get("username"), loginData.get("password"));
		System.out.println(loginData.get("username"));
		try {
			authentificationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword())
			);
		} catch (Exception ex) {
			throw new Exception ("Invalid username or Password");
		}
		
		return new ResponseEntity<>(
				jwtUtil.generateToken(authRequestDto.getEmail()), HttpStatus.OK);
	}
	
}
