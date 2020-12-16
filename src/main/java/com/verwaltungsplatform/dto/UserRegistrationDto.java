package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserRegistrationDto {

	
    @NotNull
    @NotEmpty
	private String firstName;
    
    @NotNull
    @NotEmpty
	private String lastName;
    
    @NotNull
    @NotEmpty
	private String email;
	private String password;
	
    @NotNull
    @NotEmpty
	private User_Role_RegisterCode_MapperDto role;
	
	
	// Default constructor
	public UserRegistrationDto() {
		
	}
	
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password, User_Role_RegisterCode_MapperDto role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
	
	
	
	public User_Role_RegisterCode_MapperDto getRoleCodeMapping() {
		return role;
	}




	public void setRoleCodeMapping(User_Role_RegisterCode_MapperDto role) {
		this.role = role;
	}




	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
