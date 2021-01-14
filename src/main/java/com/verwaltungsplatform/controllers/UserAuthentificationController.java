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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.dto.AuthRequestDto;
import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.dto.User_Role_RegisterCode_MapperDto;
import com.verwaltungsplatform.exceptions.UserAlreadyExistException;
import com.verwaltungsplatform.model.Family;
import com.verwaltungsplatform.model.MailSender;
import com.verwaltungsplatform.model.PasswordCode;
import com.verwaltungsplatform.model.User;
import com.verwaltungsplatform.repositories.FamilyRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.service.UserService;
import com.verwaltungsplatform.util.JwtUtil;


@Controller
public class UserAuthentificationController {
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private PasswordCode code;
	private String email;
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authentificationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FamilyRepository familyRepo;
	
	// Speichermethode der beiden Variablen sollte möglicherweise geändert werden
		String eMailUsername = "team1.verwaltungsplattform@gmail.com";
		String eMailPassword = "ToSEWS20/21T1";
	
	
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
		
		
//		String encodedPassword = bCryptPasswordEncoder.encode(userdata.get("registerPassword"));

		registrationDto.setEmail(userdata.get("registerEmail"));
		registrationDto.setPassword(userdata.get("registerPassword"));
		registrationDto.setRoleCodeMapping(mapper);
		registrationDto.setFirstName(userdata.get("registerName"));
		registrationDto.setLastName(userdata.get("registerName"));
		
		try {
			 response = userService.save(registrationDto);
			 
			 if(response.getStatusCodeValue() == 200) {
					MailSender sender = new MailSender();
					sender.login("smtp.gmail.com", "465", eMailUsername, eMailPassword);
					try {
						
						sender.send("team1.verwaltungsplattform@gmail.com", "Schule Verwaltungsplattform", registrationDto.getEmail(), "Registrierung erfolgreich", "Ihre Registrierung im System ist erfolgreich. \rSie sind nun im System registriert.");
						
					} catch(Exception e) {
						e.printStackTrace();
					}
			 }
			 
		} catch (UserAlreadyExistException e) {	
			e.printStackTrace(); 
		    return new ResponseEntity<>(
		    	      "This user might already be saved in the database", HttpStatus.CONFLICT);
		}

//		//bei Zeile 119 muss noch die familyId vom Frontend angenommen werden in der Klammer statt "familyId"
//		//der User wird hier nochmal neu aufgerufen, um die auto generated id abzufragen 
//		
//		if(registrationDto.getRoleCodeMapping().getRole().equals("Eltern")) {
//			registrationDto.setFamilyId(familyId);
//			User user = userRepo.getUserRole(registrationDto.getEmail());
//			Family family = new Family(registrationDto.getFamilyId(), user.getId());
//			
//			familyRepo.save(family);
//		}
		
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
			int userId = userService.getUserId(authRequestDto.getEmail());
			String token = jwtUtil.generateToken(authRequestDto.getEmail());
			jsonResponse.put("role", userRole);
			jsonResponse.put("userId", userId);
			jsonResponse.put("token", token);
		} 

		return new ResponseEntity<>(
				jsonResponse , HttpStatus.OK);
	}
	
	@GetMapping("/restorePassword")
	@ResponseBody
	public void generateCode(String email) {
		
		this.code = new PasswordCode();
		this.email = email;
		
		MailSender sender = new MailSender();
		sender.login("smtp.gmail.com", "465", eMailUsername, eMailPassword);
		try {
			
			sender.send("team1.verwaltungsplattform@gmail.com", "Schule Verwaltungsplattform", email, "Code zur Passwortänderung", "Sie haben versucht Ihr Passwort zu ändern. \rDer Änderungscode ist: " + code.getCode());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/restorePassword/code")
	@ResponseBody
	public boolean checkCode(int frontendCode) {
		
		return code.isCodeCorrect( frontendCode );
		
	}
	
	@PutMapping("/restorePassword/changePassword")
	@ResponseBody
	public void changePassword(String newPassword) {
		
		User user = userRepo.findByEmail(email);
		user.setPassword(newPassword);
		userRepo.save(user);
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
