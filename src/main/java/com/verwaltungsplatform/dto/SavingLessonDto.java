package com.verwaltungsplatform.dto;


public class SavingLessonDto {

	private String classId;

	private int teacherId;
	
	private String day;
   
	private int hour;
	
	private String subject;
   
    
    

   
	// Default constructor
	public SavingLessonDto() {
		
	}
	
	
	public SavingLessonDto(String classId, int teacherId, String day, int hour, String subject) {
		super();
		this.classId = classId;
		this.teacherId = teacherId;
		this.day = day;
		this.hour = hour;
		this.subject = subject;
	}



	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}
	


	
	
}
