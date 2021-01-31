package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserInfoDto {

	
	@NotNull
	@NotEmpty
	private int userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String role;

	private int familyId;
   
	private String classId;
	

   
	// Default constructor
	public UserInfoDto() {
		
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
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



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public int getFamilyId() {
		return familyId;
	}



	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}



	public String getClassId() {
		return classId;
	}



	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	
}