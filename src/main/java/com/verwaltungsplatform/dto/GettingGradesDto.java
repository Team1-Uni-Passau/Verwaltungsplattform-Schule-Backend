package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GettingGradesDto {

	
	@NotNull
	@NotEmpty
	private int affectedUserId;
	
	private String subject;
   
	private int grade;
	
	private String type;

    

   
	// Default constructor
	public GettingGradesDto() {
		
	}




	public GettingGradesDto(@NotNull @NotEmpty int affectedUserId) {
		super();
		this.affectedUserId = affectedUserId;
	}




	public int getAffectedUserId() {
		return affectedUserId;
	}




	public void setAffectedUserId(int affectedUserId) {
		this.affectedUserId = affectedUserId;
	}




	public String getSubject() {
		return subject;
	}




	public void setSubject(String subject) {
		this.subject = subject;
	}




	public int getGrade() {
		return grade;
	}




	public void setGrade(int grade) {
		this.grade = grade;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}
	
}