package com.verwaltungsplatform.controllers;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.verwaltungsplatform.dto.AuthRequestDto;
import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.dto.User_Role_RegisterCode_MapperDto;
import com.verwaltungsplatform.exceptions.UserAlreadyExistException;
import com.verwaltungsplatform.service.UserService;
import com.verwaltungsplatform.util.JwtUtil;


@Controller
public class UserAuthentificationController {
	
	

	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authentificationManager;
	
	
	// Contructor
	public UserAuthentificationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	
	
	// Method to handle registration
	@PostMapping(value = "/registration")
	public ResponseEntity<String> registerUserAccount (@RequestBody Map<String,String> userdata) throws UserAlreadyExistException {
		
		ResponseEntity<String> response;
		
		if(!this.isInteger(userdata.get("registerCode"))) {
		    return new ResponseEntity<>(
		    	      "The registration code is not valid", HttpStatus.UNPROCESSABLE_ENTITY);
		}
				
		UserRegistrationDto registrationDto = new UserRegistrationDto();
		User_Role_RegisterCode_MapperDto mapper = new User_Role_RegisterCode_MapperDto();
			
		mapper.setRole(userdata.get("roleCheckedInRegisterForm"));
		mapper.setRegisterCode(Integer.valueOf(userdata.get("registerCode")));
		
		registrationDto.setEmail(userdata.get("registerEmail"));
		registrationDto.setPassword(userdata.get("registerPassword"));
		registrationDto.setRoleCodeMapping(mapper);
		registrationDto.setFirstName(userdata.get("registerName"));
		registrationDto.setLastName(userdata.get("registerName"));
    	
		
		try {
			 response = userService.save(registrationDto);
		} catch (UserAlreadyExistException e) {	
			e.printStackTrace(); 
		    return new ResponseEntity<>(
		    	      "This user might already be saved in the database", HttpStatus.CONFLICT);
		}
		
	    return response;

	}
	
	
	
	// Method to handle login requests
	@PostMapping(value= "/login")
	public ResponseEntity<Map<String,Object>> generateToken(@RequestBody Map<String,String> loginData) throws Exception {
		
		
	    Map<String, Object> jsonResponse = new HashMap<String, Object>();

		AuthRequestDto authRequestDto = new AuthRequestDto(loginData.get("username"), loginData.get("password"));
		
		try {
			authentificationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword())
			);
		} catch (Exception ex) {
			ex.printStackTrace(); 
			jsonResponse.put("message", "Wrong username or password");
			return new ResponseEntity<>(
					jsonResponse , HttpStatus.OK);
		}
				
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal != null) {
			String userRole = userService.getUserRole(authRequestDto.getEmail());
			String token = jwtUtil.generateToken(authRequestDto.getEmail());
			jsonResponse.put("role", userRole);
			jsonResponse.put("token", token);
		} 

		return new ResponseEntity<>(
				jsonResponse , HttpStatus.OK);
	}
	
	
	
	
	// Method to check if the register code is of type integer
	public boolean isInteger(String p_str)
	{
	    if (p_str == null)
	        return false;
	    else
	        return p_str.matches("^\\d*$");
	}

	
}
