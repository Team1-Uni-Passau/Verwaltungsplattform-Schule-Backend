package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User_Role_RegisterCode_MapperDto {

	
    @NotNull
    @NotEmpty
	private String userRole;
	
    
    @NotNull
    @NotEmpty
	private int registrationCode;
	
	
	
	
	//default constructor
	public User_Role_RegisterCode_MapperDto() {

	}

	

	public User_Role_RegisterCode_MapperDto(String role, int registerCode) {
		super();
		this.userRole = role;
		this.registrationCode = registerCode;
	}
	
	
	
	
	
	
	public String getRole() {
		return userRole;
	}

	public void setRole(String role) {
		this.userRole = role;
	}

	public int getRegisterCode() {
		return registrationCode;
	}

	public void setRegisterCode(int registerCode) {
		this.registrationCode = registerCode;
	}

}
