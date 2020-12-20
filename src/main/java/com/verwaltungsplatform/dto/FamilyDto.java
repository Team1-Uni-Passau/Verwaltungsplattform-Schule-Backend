package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FamilyDto {

	
	@NotNull
	@NotEmpty
	private int parentId;
	
	private int familyId;
	
	private int studentId;
   
	private String classId;
	

   
	// Default constructor
	public FamilyDto() {
		
	}
	
	
	public FamilyDto(int parentId) {
		super();
		
		this.parentId = parentId;
		this.familyId = 0;
		this.studentId = 0;
		this.classId = "";
	}


	public int getFamilyId() {
		return familyId;
	}


	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}


	
}
