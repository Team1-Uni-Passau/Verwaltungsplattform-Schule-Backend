package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LessonDto {

	
	@NotNull
	@NotEmpty
	private int affectedUserId;
	
	private String day;
   
	private int hour;
	
	private String subject;
   
    private String classid;
    

   
	// Default constructor
	public LessonDto() {
		
	}
	
	
	public LessonDto(int affectedUserId) {
		super();
		
		this.day = "";
		this.hour = 0;
		this.subject = "";
		this.classid="";
	}


	public int getAffectedUserId() {
		return affectedUserId;
	}


	public void setAffectedUserId(int affectedUserId) {
		this.affectedUserId = affectedUserId;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	

	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getClassid() {
		return classid;
	}


	public void setClassid(String classid) {
		this.classid = classid;
	}
	


	
	
}
