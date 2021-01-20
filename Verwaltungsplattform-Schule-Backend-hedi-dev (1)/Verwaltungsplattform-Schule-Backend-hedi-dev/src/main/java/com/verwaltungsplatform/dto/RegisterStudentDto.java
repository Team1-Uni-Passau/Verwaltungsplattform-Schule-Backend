package com.verwaltungsplatform.dto;


public class RegisterStudentDto {

	
	private String firstName;
	
	private String lastName;
	
	private String classId;
   
	private int userId;
	
	private int familyId;
	

   
	// Default constructor
	public RegisterStudentDto() {
		
	}



	public RegisterStudentDto(String firstName, String lastName, String classId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.classId = classId;
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



	public String getClassId() {
		return classId;
	}



	public void setClassId(String classId) {
		this.classId = classId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getFamilyId() {
		return familyId;
	}



	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	
}