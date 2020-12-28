package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GivingGradesDto {

	
	@NotNull
	@NotEmpty
	private int affectedUserId;
	
	private int examId;
	
	private int grade;
	   

   
	// Default constructor
	public GivingGradesDto() {
		
	}




	public GivingGradesDto(@NotNull @NotEmpty int affectedUserId) {
		super();
		this.affectedUserId = affectedUserId;
	}

	public GivingGradesDto(int affectedUserId, int examId, int grade) {
		super();
		this.affectedUserId = affectedUserId;
		this.examId = examId;
		this.grade = grade;
	}


	public int getAffectedUserId() {
		return affectedUserId;
	}




	public void setAffectedUserId(int affectedUserId) {
		this.affectedUserId = affectedUserId;
	}




	public int getExamId() {
		return examId;
	}




	public void setExamId(int examId) {
		this.examId = examId;
	}




	public int getGrade() {
		return grade;
	}




	public void setGrade(int grade) {
		this.grade = grade;
	}




	}